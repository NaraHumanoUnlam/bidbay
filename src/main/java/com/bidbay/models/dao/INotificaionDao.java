package com.bidbay.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Notificacion;

public interface INotificaionDao extends CrudRepository<Notificacion, Long>{

	@Query(value = "select * from notificaciones where id=?", nativeQuery = true)
	public Notificacion obtenerNotificacion(Long id);
}
