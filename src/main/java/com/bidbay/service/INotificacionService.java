package com.bidbay.service;


import java.util.List;

import com.bidbay.models.entity.Notificacion;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;

public interface INotificacionService{

	public Notificacion obtenerNotificacion(Long id);
	public List<Notificacion> findAll();
	public void save(Notificacion notificacion);
	public void eliminarNotificacion(Long id);
	void crearNotificacion(String titulo, String notificacion, Long idUsuario,String enlace);
	public List<Notificacion> findAllByUser(Long userID);


	
	
}
