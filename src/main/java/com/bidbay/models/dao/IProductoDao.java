package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bidbay.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	@Query(value = "select * from productos where usuario_id =?1", nativeQuery = true)
	public List<Producto> detallesProducto(Long idUsuario);
	
    @Query(value = "SELECT IFNULL(SUM(dc.cantidad), 0) AS cant FROM detalle_compras dc JOIN compras c ON dc.compra_id = c.id JOIN productos p ON dc.productos = p.id WHERE p.usuario_id = ?1", nativeQuery = true)
    public Integer cantidadVentas(Long idUsuario);
    
    @Query(value = "SELECT IFNULL(SUM(dc.cantidad), 0) AS cant FROM detalle_compras dc JOIN compras c ON dc.compra_id = c.id WHERE c.id_usuario = ?1", nativeQuery = true)
    public Integer cantidadCompras(Long idUsuario);
}
