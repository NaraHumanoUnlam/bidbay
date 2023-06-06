package com.bidbay.service;

import java.util.List;
import com.bidbay.models.entity.Carrito;

public interface ICarritoService {
	
	public List <Carrito> findAll(); 
	public void save(Carrito carrito);
	public Carrito findOne(Long id);
    public void delete(Long id);


}
