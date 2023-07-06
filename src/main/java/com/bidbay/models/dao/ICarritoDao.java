package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;


public interface ICarritoDao extends CrudRepository<Carrito, Long>{
	
	@Query(value = "select * from carrito where id_usuario=?1", nativeQuery = true)
	public Carrito findCarritoFromUser(Long id_usuario);

}
