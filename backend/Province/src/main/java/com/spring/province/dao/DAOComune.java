package com.spring.province.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.province.entity.Comune;
import com.spring.province.entity.Provincia;


public interface DAOComune extends JpaRepository<Comune,Integer>{
	List<Comune> findByProvincia(Provincia provincia);
}
