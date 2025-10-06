package com.spring.province.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.province.dao.DAOComune;
import com.spring.province.dao.DAOProvincia;
import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Comune;
import com.spring.province.entity.Provincia;
import com.spring.province.utility.Conversioni;

import jakarta.transaction.Transactional;




@Service
public class ServiceProvinciaImpl implements ServiceProvinciaInterface{
	
	@Autowired
	private DAOProvincia dao;
	
	@Autowired
	private DAOComune daoComune;
	
	@Override
	public List<ProvinciaDTO> findAll() {
		List<Provincia> province = dao.findAll();
		List<ProvinciaDTO> provinceDTO = new ArrayList<>();
		for(Provincia provincia: province) {
			ProvinciaDTO dto = Conversioni.daProvinciaAProvinciaDTO(provincia);
			provinceDTO.add(dto);
		}
		return provinceDTO;
	}

	@Override
	@Transactional
	public boolean insertProvincia(ProvinciaDTO provinciaDTO) {
		Provincia provincia = Conversioni.daProvinciaDTOAProvincia(provinciaDTO);
		Optional<Provincia> opt = dao.findById(provinciaDTO.getSigla_provincia());
		if(opt.isPresent()) {
			System.out.println("Provincia già presente: " + opt.get());
			return false;
		}else{
			System.out.println("Salvataggio nuova provincia: " + provincia);
			dao.save(provincia);
			return true;
		}
	}

	@Override
	@Transactional
	public boolean deleteProvincia(String sigla_provincia) {
		Optional<Provincia> opt = dao.findById(sigla_provincia);
		if(opt.isPresent()) {
			Provincia provincia = opt.get();
			System.out.println("Provincia esistente");
			List<Comune> comuni = daoComune.findByProvincia(provincia);
			daoComune.deleteAll(comuni);
			dao.delete(provincia);
			return true;
		}else {
			System.out.println("La provincia non esiste");
			return false;
		}
	}

	@Override
	public boolean updateProvincia(String sigla_provincia,ProvinciaDTO newProvincia) {
		Optional<Provincia> opt = dao.findById(sigla_provincia);
		if(opt.isPresent()) {
			Provincia provincia = opt.get();
			System.out.println("Provincia esistente");
			provincia.setCodice_regione(newProvincia.getCodice_regione());
			provincia.setDenominazione_provincia(newProvincia.getDenominazione_provincia());
			provincia.setTipologia_provincia(newProvincia.getTipologia_provincia());
			provincia.setNumero_comuni(newProvincia.getNumero_comuni());
			provincia.setSuperficie_kmq(newProvincia.getSuperficie_kmq());
			provincia.setCodice_sovracomunale(newProvincia.getCodice_sovracomunale());
			dao.save(provincia);
			return true;
		}else {
			System.out.println("Provincia non trovata");
			return false;
		}
	}

	@Override
	public ProvinciaDTO getProvincia(String sigla_provincia) {
		Optional<Provincia> opt = dao.findById(sigla_provincia);
		if(opt.isPresent()) {
			Provincia provincia = opt.get();
			System.out.println("Provincia esistente");
			ProvinciaDTO dto = Conversioni.daProvinciaAProvinciaDTO(provincia);
			return dto;
		}
		return null;
	}
	
	/*Transazione senza transactional
	@Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public boolean deleteProvincia(String sigla_provincia) {
        // Definisci una nuova transazione
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("DeleteProvinciaTransaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);

        // Avvia la transazione
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            Optional<Provincia> opt = dao.findById(sigla_provincia);
            if (opt.isPresent()) {
                Provincia provincia = opt.get();
                System.out.println("Provincia esistente");

                List<Comune> comuni = daoComune.findByProvincia(provincia);
                daoComune.deleteAll(comuni);
                dao.delete(provincia);

                transactionManager.commit(status); // commit se tutto ok
                return true;
            } else {
                System.out.println("La provincia non esiste");
                transactionManager.rollback(status); // rollback anche se non trovi nulla
                return false;
            }
        } catch (Exception e) {
            transactionManager.rollback(status); // rollback in caso di eccezioni
            throw e; // rilancia per far sapere che qualcosa è andato storto
        }
    }
}*/

	

}
