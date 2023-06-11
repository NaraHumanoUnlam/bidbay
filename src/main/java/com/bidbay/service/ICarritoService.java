package com.bidbay.service;

import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	void addProductToCarrito(Long idUsuario, Long idProducto, RedirectAttributes redirectAttributes);
	void editCarritoItem(Long idUsuario, Long id, Integer stock, RedirectAttributes redirectAttributes);
	void deleteCarritoItem(Long idUsuario, Long id, RedirectAttributes redirectAttributes);
	void changeCarritoItemStock(CarritoItem carritoItem, Carrito carrito, Integer stock, RedirectAttributes redirectAttributes);
	double calcularPrecioTotal(Long idUsuario);
}
