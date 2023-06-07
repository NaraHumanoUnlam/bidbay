package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Categoria;

public interface ICategoriaService {

	  public List <Categoria> findAll(); 
	  public void save(Categoria c);
	  public Categoria findOne(Long id);
      public void delete(Long id);
    
	
}
