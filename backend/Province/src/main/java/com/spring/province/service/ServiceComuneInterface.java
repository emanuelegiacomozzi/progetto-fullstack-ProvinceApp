package com.spring.province.service;

import java.util.List;

import com.spring.province.dto.ComuneDTO;
import com.spring.province.entity.Provincia;

public interface ServiceComuneInterface {
	
	public List<ComuneDTO> findByProvincia(String sigla_provincia);
	public boolean insert(ComuneDTO comuneDTO,String sigla_provincia);
	public boolean delete(int codice_istat);
	public List<ComuneDTO> getAll();
	public boolean update(int codice_istat, ComuneDTO newComune);
	public ComuneDTO getComune(String sigla_provincia, int codice_istat);
}
