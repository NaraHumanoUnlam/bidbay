package com.bidbay.service;


import java.util.List;

import com.bidbay.models.entity.Notificacion;
import com.bidbay.models.entity.Producto;

public interface INotificacionService{

	public Notificacion obtenerNotificacion(Long id);
	public List<Notificacion> findAll();
	public void save(Notificacion notificacion);

	
	
}
