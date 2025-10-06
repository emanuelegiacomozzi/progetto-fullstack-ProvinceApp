package com.spring.province.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.province.entity.Provincia;

public interface DAOProvincia extends JpaRepository<Provincia,String>{
	
}
