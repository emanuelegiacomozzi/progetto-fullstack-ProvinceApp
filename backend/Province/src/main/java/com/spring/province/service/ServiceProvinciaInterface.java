package com.spring.province.service;

import java.util.List;

import com.spring.province.dto.ProvinciaDTO;
import com.spring.province.entity.Provincia;

public interface ServiceProvinciaInterface {
	public List<ProvinciaDTO> findAll();
	public boolean insertProvincia(ProvinciaDTO provinciaDTO);
	public boolean deleteProvincia(String sigla_provincia);
	public boolean updateProvincia(String sigla_provincia,ProvinciaDTO provincia);
	public ProvinciaDTO getProvincia(String sigla_provincia);
}
