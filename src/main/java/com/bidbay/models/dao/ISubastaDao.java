package com.bidbay.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Subasta;

public interface ISubastaDao extends CrudRepository<Subasta, Long>{
	@Query(value = "select * from subastas where id=?", nativeQuery = true)
	public Subasta obtenerSubasta(Long id);
}
