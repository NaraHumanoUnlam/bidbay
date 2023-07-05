package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.DetalleCompras;

public interface IDetalleCompra extends CrudRepository<DetalleCompras, Long>{
		@Query(value = "select * from detalle_compras where compra_id=?1", nativeQuery = true)
		public List<DetalleCompras> detallesCompras(Long idcompra);
}