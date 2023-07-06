package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;

public interface ICarritoItemDao extends CrudRepository<CarritoItem, Long>{
	
	@Query(value = "select * from carrito_item where carrito_id=?1", nativeQuery = true)
	public List<CarritoItem> findCarritoItemsFromCarrito(Long id_carrito);

}
