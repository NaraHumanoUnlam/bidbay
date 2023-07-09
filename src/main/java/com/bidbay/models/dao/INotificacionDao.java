package com.bidbay.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Notificacion;

public interface INotificacionDao extends CrudRepository<Notificacion, Long>{

	@Query(value = "select * from notificacion where id=?", nativeQuery = true)
	public Notificacion obtenerNotificacion(Long id);
	
	@Modifying
	@Query(value = "insert into notificacion (fecha, titulo, notificacion, usuarios_id,enlace) values (current_date(),?,?,?,?)", nativeQuery = true)
	public void crearNotificacion(String titulo, String texto, Long idUsuario, String enlace);
	
	
	@Modifying
	@Query(value = "delete from notificacion where id = ?", nativeQuery = true)
	public void eliminarNotificacion(Long id);
	
	@Query(value = "select * from notificacion where usuarios_id=?", nativeQuery = true)
	public List<Notificacion> findAllByID(Long userID);
}
