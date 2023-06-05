package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Compra;

public interface ICompraService {

	 public List <Compra> findAll(); 
	  public void save(Compra compra);
	  public Compra findOne(Long id);
     public void delete(Long id);
}
