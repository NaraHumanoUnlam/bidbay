package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Modalidad;

public interface IModalidadService {
	  public List <Modalidad> findAll(); 
	  public void save(Modalidad c);
	  public Modalidad findOne(Long id);
	  public void delete(Long id);
}
