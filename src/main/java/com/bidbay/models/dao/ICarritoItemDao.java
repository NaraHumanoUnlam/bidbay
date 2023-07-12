package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.CarritoItem;

import jakarta.transaction.Transactional;




public interface ICarritoItemDao extends CrudRepository<CarritoItem, Long> {

    @Query(value = "SELECT * FROM carrito_item WHERE carrito_id = ?1", nativeQuery = true)
    public List<CarritoItem> findCarritoItemsFromCarrito(Long id_carrito);
    
    /*@Modifying
    @Transactional
    @Query(value = "DELETE FROM carrito_item WHERE productos = ?1", nativeQuery = true)
	public void deleteByProductoId(Long id);*/



}
