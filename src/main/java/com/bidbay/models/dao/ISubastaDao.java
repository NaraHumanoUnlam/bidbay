package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bidbay.models.entity.Compra;

public interface ISubastaDao extends CrudRepository<Compra, Long>{

//	 @Query("SELECT s FROM subasta s WHERE s.idOfertante = :value")
//	 List<Compra> findByIdOfertante(@Param("value") Long idUsuario);
//	 
//	 @Query("SELECT s FROM subasta s WHERE s.idSubastador = :value")
//	 List<Compra> findByIdSubastador(@Param("value") Long idUsuario);
}
