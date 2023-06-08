package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;

public interface ICarritoService {
	
	List<Carrito> findAll();
    void save(Carrito carrito);
    Carrito findOne(Long id);
    Carrito findOneByUserID(Long id);
    void delete(Long id);
    
	CarritoItem findCarritoItemById(Long idItem, Long idCarrito);
	void deleteCarritoItem(CarritoItem carritoItem, Long idCarrito);
	void saveCarritoItem(CarritoItem carritoItem, Long idCarrito);

}
