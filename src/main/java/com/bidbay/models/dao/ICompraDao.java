package com.bidbay.models.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;

public interface ICompraDao extends CrudRepository<Compras, Long>{
	
	@Query(value = "select * from compras where id_usuario=?1", nativeQuery = true)
	public List<Compras> comprasDelusuario(Long id_usuario);
	
	@Query(value = "UPDATE compras"
			+ "SET fecha = ?, id_pago = ?"
			+ "id = ?", nativeQuery = true)
	public List<Compras> actualizarFechaYidPago(Date fecha, Long id, Long idCompra);
	
	@Query(value = "select * from compras where id_usuario=?1 and id_pago is null", nativeQuery = true)
	public List<Compras> comprasSinPagarDelusuario(Long id);
	
	@Query(value = "select * from compras where id_usuario=?1 and monto = ?", nativeQuery = true)
	public Compras obtenerCompraDeUsuario(Long id, Double monto);
}
