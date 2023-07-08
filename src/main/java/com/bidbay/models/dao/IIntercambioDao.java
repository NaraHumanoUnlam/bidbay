package com.bidbay.models.dao;

import java.util.List;
import com.bidbay.models.entity.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IIntercambioDao extends CrudRepository<Intercambio, Long>{
	@Query(value = "select * from intercambio", nativeQuery = true)
	public List<Intercambio> listar();
	
}
