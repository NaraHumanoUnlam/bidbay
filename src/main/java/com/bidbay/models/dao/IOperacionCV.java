package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.OperacionCV;

public interface IOperacionCV extends CrudRepository<OperacionCV, Long> {
    @Query(value = "SELECT c.id AS compra,\r\n"
    		+ "       c.id_pago AS pago,\r\n"
    		+ "       c.id_usuario AS usuario_compra,\r\n"
    		+ "       c.fecha,\r\n"
    		+ "       dc.cantidad,\r\n"
    		+ "       dc.precio_compra,\r\n"
    		+ "       p.id AS producto,\r\n"
    		+ "       p.descripcion AS descripcion_producto,\r\n"
    		+ "       p.imagen AS imagen_producto,\r\n"
    		+ "       p.usuario_id AS usuario_vende\r\n"
    		+ "FROM detalle_compras dc\r\n"
    		+ "JOIN compras c ON dc.compra_id = c.id\r\n"
    		+ "JOIN productos p ON dc.productos = p.id\r\n"
    		+ "WHERE  p.usuario_id=?1", nativeQuery = true)
    public List<OperacionCV> detalleVentas(Long idUsuario);
    
    @Query(value = "SELECT c.id AS compra,\r\n"
    		+ "       c.id_pago AS pago,\r\n"
    		+ "       c.id_usuario AS usuario_compra,\r\n"
    		+ "       c.fecha,\r\n"
    		+ "       dc.cantidad,\r\n"
    		+ "       dc.precio_compra,\r\n"
    		+ "       p.id AS producto,\r\n"
    		+ "       p.descripcion AS descripcion_producto,\r\n"
    		+ "       p.imagen AS imagen_producto,\r\n"
    		+ "       p.usuario_id AS usuario_vende\r\n"
    		+ "FROM detalle_compras dc\r\n"
    		+ "JOIN compras c ON dc.compra_id = c.id\r\n"
    		+ "JOIN productos p ON dc.productos = p.id\r\n"
    		+ "WHERE c.id_usuario = ?1", nativeQuery = true)
    public List<OperacionCV> detalleCompras(Long idUsuario);
}
