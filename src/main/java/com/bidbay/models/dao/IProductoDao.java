package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.bidbay.models.entity.Producto;


public interface IProductoDao extends CrudRepository<Producto, Long>{
	@Query(value = "select * from productos where usuario_id =?", nativeQuery = true)
	public List<Producto> detallesProducto(Long idUsuario);
	
	@Modifying
	@Query(value = "UPDATE productos "
	 		+ "SET Stock = Stock-? " 
	 		+ "WHERE id = ? ",nativeQuery = true )
	 public void descontarStock(Integer cantidad, Long id);
	
	
	@Modifying
	@Query(value = "UPDATE productos "
	 		+ "SET Stock = Stock+? " 
	 		+ "WHERE id = ? ",nativeQuery = true )
	 public void restituirStock(Integer cantidad, Long id);
	
	
	@Query(value = "select count(*) from productos where usuario_id =? and id=?", nativeQuery = true)
	public int productoEsVendidoPorUsuario(Long idUsuario, Long idProducto);
	
	@Query(value = "select stock from productos where id=?", nativeQuery = true)
	public Integer traerStockDisponible(Long idProducto);
	
	@Modifying
	@Query(value = "Update productos set stock = 0 where id = ?", nativeQuery = true)
	public void deleteFromView(Long id);
}
