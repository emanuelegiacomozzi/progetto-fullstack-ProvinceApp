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

import com.spring.province.dao.DAOProvincia;
import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Provincia;
import com.spring.province.service.ServiceProvinciaImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  
@RequestMapping(path="/province")
public class ControllerProvincia {
	
	@Autowired
	private ServiceProvinciaImpl service;
	
	@Autowired
	private DAOProvincia daoProvincia;
	
	public static final Logger logger = LoggerFactory.getLogger(ControllerProvincia.class);
	
	@GetMapping()
	public List<ProvinciaDTO> selectAll(){
		logger.info("Richiesta ricevuta per ottenere tutte le province");
		try {
			List<ProvinciaDTO> province = service.findAll();
			logger.debug("Numero province trovate: {} ", province.size());
			return province;
		}catch (Exception e) {
			logger.error("Errore durante il recupero delle province", e);
			throw e;
		}
	}
	
	@PostMapping()
	public boolean insertProvincia(@RequestBody ProvinciaDTO provinciaDTO){
		logger.info("Richiesta ricevuta per l'inserimento della provincia di {} ", provinciaDTO.getDenominazione_provincia());
		try {
			logger.debug("Inserimento della provincia di {} ", provinciaDTO.getDenominazione_provincia() , " riuscito.");
			return service.insertProvincia(provinciaDTO);
		}catch (Exception e) {
			logger.error("Errore durante l'inserimento della provincia di {} ", provinciaDTO.getDenominazione_provincia() ,e);
			throw e;
		}
	}
	
	@DeleteMapping("/{sigla_provincia}")
	public boolean deleteProvincia(@PathVariable String sigla_provincia) {
		Optional<Provincia> opt = daoProvincia.findById(sigla_provincia);
		Provincia provincia = opt.get();
		logger.info("Richiesta ricevuta per l'eliminazione della provincia di {} ", provincia.getDenominazione_provincia());
		try {
			logger.debug("Eliminazione della provincia di {} " , provincia.getDenominazione_provincia(), " riuscita.");
			return service.deleteProvincia(sigla_provincia);
		}catch(Exception e) {
			logger.error("Errore durante l'eliminazione della provincia di {} ", provincia.getDenominazione_provincia(), e);
			throw e;
		}
	}
	
	
	@PatchMapping("/{sigla_provincia}")
	public boolean updateProvincia(@PathVariable String sigla_provincia, @RequestBody ProvinciaDTO newProvincia) {
		Optional<Provincia> opt = daoProvincia.findById(sigla_provincia);
		Provincia provincia = opt.get();
		logger.info("Richiesta ricevuta per la modifica della provincia di {} ", provincia.getDenominazione_provincia());
		try {
			logger.debug("Modifica della provincia di {} " , provincia.getDenominazione_provincia(), " riuscita.");
			return service.updateProvincia(sigla_provincia,newProvincia);
		}catch(Exception e) {
			logger.error("Errore durante la modifica della provincia di {} ",  provincia.getDenominazione_provincia() ,e);
			throw e;
		}
	}
	
	
	@GetMapping("/{sigla_provincia}")
	public ProvinciaDTO getProvincia(@PathVariable String sigla_provincia) {
		Optional<Provincia> opt = daoProvincia.findById(sigla_provincia);
		Provincia provincia = opt.get();
		logger.info("Richiesta ricevuta per ottenere la provincia di {} ", provincia.getDenominazione_provincia());
		try {
			logger.debug("Richiesta per ottenere la provincia di {} ", provincia.getDenominazione_provincia() ," avvenuta con successo");
			return service.getProvincia(sigla_provincia);
		}catch(Exception e){
			logger.error("Errore durante la richiesta della provincia di {} ", provincia.getDenominazione_provincia(), e);
			throw e;
		}
	}
	

}
