package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.bidbay.models.entity.Producto;


public interface IProductoDao extends CrudRepository<Producto, Long>{
	@Query(value = "select * from productos where usuario_id =?1", nativeQuery = true)
	public List<Producto> detallesProducto(Long idUsuario);
	
	@Modifying
	 @Query(value = "UPDATE productos "
	 		+ "SET Stock = Stock-? " 
	 		+ "WHERE id = ? ",nativeQuery = true )
	 public void actualizarStock(Integer cantidad, Long id);
	
}
