package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;

public interface ICompraDao extends CrudRepository<Compras, Long>{
	
	@Query(value = "select * from compras where id_usuario=?1", nativeQuery = true)
	public List<Compras> comprasDelusuario(Long id_usuario);
}
