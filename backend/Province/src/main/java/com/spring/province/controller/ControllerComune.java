package com.spring.province.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.province.dao.DAOComune;
import com.spring.province.dto.ComuneDTO;
import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Comune;
import com.spring.province.service.ServiceComuneImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/comuni")
public class ControllerComune {
	
	@Autowired
	private ServiceComuneImpl service;
	
	@Autowired
	private DAOComune daoComune;
	
    public static final Logger logger = LoggerFactory.getLogger(ControllerComune.class);
	
	@GetMapping("/provincia/{sigla_provincia}")
	public List<ComuneDTO> getComuniByProvincia(@PathVariable String sigla_provincia){
		
		logger.info("Richiesta ricevuta per ottenere i comuni di {} " , sigla_provincia);
		try {
			logger.debug("Numero comuni trovati: {} ", service.findByProvincia(sigla_provincia).size());
		}catch(Exception e) {
			logger.error("Errore nel caricamento dati" , e);
			throw e;
		}
		return service.findByProvincia(sigla_provincia);
	}
	
	@PostMapping("/{sigla_provincia}")
	public boolean insertComuneByProvincia(@RequestBody ComuneDTO comuneDTO, @PathVariable String sigla_provincia) {
		logger.info("Richiesta ricevuta per l'inserimento del comune di {} ", comuneDTO.getDenominazione_ita_altra());
		try {
			logger.debug("Inserimento del comune di {} ", comuneDTO.getDenominazione_ita_altra() ," riuscito.");
			return service.insert(comuneDTO, sigla_provincia);
		}catch(Exception e) {
			logger.error("Errore durante l'inserimento del comune di {} " , comuneDTO.getDenominazione_ita_altra() , e);
			throw e;
		}
	}
	
	@DeleteMapping("/{codice_istat}")
	public boolean deleteComuneByCodice(@PathVariable int codice_istat) {
		Optional<Comune> opt = daoComune.findById(codice_istat);
		Comune comune = opt.get();
		logger.info("Richiesta ricevuta per l'eliminazione del comune di {} ", comune.getDenominazione_ita_altra());
		try {
			logger.debug("Eliminazione del comune di {} " , comune.getDenominazione_ita_altra() , " effettuata correttamente");
			return service.delete(codice_istat);
		}catch(Exception e){
			logger.error("Errore durante l'eliminazione del comune di {} ", comune.getDenominazione_ita_altra() , e);
			throw e;
		}
	}
	
	
	@GetMapping()
	public List<ComuneDTO> getAll(){
		logger.info("Richiesta ricevuta per ottenere tutti i comuni");
		try {
			List<ComuneDTO> comuni = service.getAll();
			logger.debug("Numero comuni trovati: {}", comuni.size());
			return comuni;
		}catch(Exception e){
			logger.error("Errore durante il recupero dei comuni", e);
			throw e;
		}
	}
	
	
	
	@PatchMapping("/{codice_istat}")
	public boolean updateComuneByCodice(@PathVariable int codice_istat, @RequestBody ComuneDTO newComune) {
		Optional<Comune> opt = daoComune.findById(codice_istat);
		Comune comune = opt.get();
		logger.info("Richiesta ricevuta per la modifica del comune di {}", comune.getDenominazione_ita_altra());
		try {
			logger.debug("Modifica del comune di {} " + comune.getDenominazione_ita_altra() + " avvenuta correttamente");
			return service.update(codice_istat, newComune);
		}catch(Exception e) {
			logger.error("Errore durante la modifca del comune di {} "+ comune.getDenominazione_ita_altra(), e);
			throw e;
		}
	}
	
	
	@GetMapping("/{sigla_provincia}/{codice_istat}")
	public ComuneDTO getComuneByProvincia(@PathVariable String sigla_provincia, @PathVariable int codice_istat) {
		Optional<Comune> opt = daoComune.findById(codice_istat);
		Comune comune = opt.get();
		logger.info("Richiesta ricevuta per ottenere il comune di {} ", comune.getDenominazione_ita_altra());
		try {
			logger.debug("Richiesta per ottenere il comune di {} ", comune.getDenominazione_ita_altra() ,"avvenuta correttamente");
			return service.getComune(sigla_provincia, codice_istat);
		}catch(Exception e) {
			logger.error("Errore durante la richiesta del comune di {} ", comune.getDenominazione_ita_altra() , e);
			throw e;
		}
	}
	
	
}
