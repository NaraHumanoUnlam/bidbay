package com.bidbay.models.dao;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.ReporteProducto;

import jakarta.transaction.Transactional;

public interface IReporteProductoDao extends CrudRepository<ReporteProducto, Long>{

	
	@Modifying
	@Transactional
	@Query(value = "delete from reporte_producto where producto_id=?", nativeQuery = true)
	void borrarPorIdProducto(Long id);

}
