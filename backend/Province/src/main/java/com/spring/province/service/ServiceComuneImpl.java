package com.spring.province.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.province.dao.DAOComune;
import com.spring.province.dao.DAOProvincia;
import com.spring.province.dto.ComuneDTO;
import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Comune;
import com.spring.province.entity.Provincia;
import com.spring.province.utility.Conversioni;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceComuneImpl implements ServiceComuneInterface{
	
	@Autowired
	private DAOComune dao;
	
	@Autowired
	private DAOProvincia daoProvincia;

	@Override
	public List<ComuneDTO> findByProvincia(String sigla_provincia) {
		
		Provincia provincia = new Provincia();
        provincia.setSigla_provincia(sigla_provincia); 
        List<Comune> comuni = dao.findByProvincia(provincia);
        List<ComuneDTO> comuniDTO = new ArrayList<>();
        for(Comune comune: comuni) {
        	ComuneDTO dto = Conversioni.daComuneAComuneDTO(comune);
        	comuniDTO.add(dto);
        }
        return comuniDTO;
		
	}

	@Override
	@Transactional
	public boolean insert(ComuneDTO comuneDTO, String sigla_provincia) {
		Comune comune = Conversioni.daComuneDTOAComune(comuneDTO);
		Optional<Comune> opt = dao.findById(comuneDTO.getCodice_istat());
		Optional<Provincia> provinciaOpt = daoProvincia.findById(sigla_provincia);
		if(opt.isPresent() || !comuneDTO.getProvincia().getSigla_provincia().equals(sigla_provincia) || !provinciaOpt.isPresent()) {
			System.out.println("Comune già presente: " + opt.get());
			return false;
		}else {
			System.out.println("Salvataggio nuovo comune: " + comune);
			comune.setProvincia(provinciaOpt.get());
			dao.save(comune);
			Provincia provincia = comune.getProvincia(); 
			provincia.setNumero_comuni(provincia.getNumero_comuni()+1);
			return true;
		}
		
	}

	@Override
	@Transactional
	public boolean delete(int codice_istat) {
		Optional<Comune> opt = dao.findById(codice_istat);
		if(opt.isPresent()) {
			Comune comune = opt.get();
			System.out.println("Comune esistente");
			dao.delete(comune);
			Provincia provincia = comune.getProvincia(); 
			provincia.setNumero_comuni(provincia.getNumero_comuni()-1);
			return true;
		}else {
			System.out.println("Il comune non esiste");
			return false;
		}
	}

	@Override
	public List<ComuneDTO> getAll() {
		List<Comune> comuni = dao.findAll();
		List<ComuneDTO> comuniDTO = new ArrayList<>();
		for(Comune comune:comuni) {
			ComuneDTO comuneDTO = Conversioni.daComuneAComuneDTO(comune);
			comuniDTO.add(comuneDTO);
		}
		return comuniDTO;
	}

	@Override
	public boolean update(int codice_istat, ComuneDTO newComune) {
		Optional<Comune> opt = dao.findById(codice_istat);
		if(opt.isPresent()) {
			Comune comune = opt.get();
			System.out.println("Comune esistente");
			
			ProvinciaDTO provinciaDTO = newComune.getProvincia();
			Optional<Provincia> provinciaOpt = daoProvincia.findById(provinciaDTO.getSigla_provincia());
			if (provinciaOpt.isEmpty()) {
	            System.out.println("Provincia non trovata");
	            return false;
	        }
			Provincia provincia = provinciaOpt.get();
			comune.setProvincia(provincia);
			
			comune.setDenominazione_ita_altra(newComune.getDenominazione_ita_altra());
			comune.setDenominazione_ita(newComune.getDenominazione_ita());
			comune.setDenominazione_altra(newComune.getDenominazione_altra());
			comune.setFlag_capoluogo(newComune.getFlag_capoluogo());
			comune.setLat(newComune.getLat());
			comune.setLon(newComune.getLon());
			comune.setSuperficie_kmq(newComune.getSuperficie_kmq());
			comune.setCodice_sovracomunale(newComune.getCodice_sovracomunale());
			dao.save(comune);
			return true;
		}else {
			System.out.println("Comune non trovato");
			return false;
		}
	}

	@Override
	public ComuneDTO getComune(String sigla_provincia, int codice_istat) {
		Optional<Comune> opt = dao.findById(codice_istat);
		if(opt.isPresent()) {
			Comune comune = opt.get();
			if(comune.getProvincia() != null && sigla_provincia.equals(comune.getProvincia().getSigla_provincia())) {
				System.out.println("Comune e provincia corrispondenti trovati");
				ComuneDTO dto = Conversioni.daComuneAComuneDTO(comune);
				return dto;
			}
		}
		System.out.println("Comune e provincia corrispondenti non trovati");
		return null;
	}
		
}
	
	
	
	
	
	
	

