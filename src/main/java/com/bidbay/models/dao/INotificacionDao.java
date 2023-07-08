package com.bidbay.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Notificacion;

public interface INotificacionDao extends CrudRepository<Notificacion, Long>{

	@Query(value = "select * from notificacion where id=?", nativeQuery = true)
	public Notificacion obtenerNotificacion(Long id);

	
}
