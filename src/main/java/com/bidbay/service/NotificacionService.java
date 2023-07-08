package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.entity.Notificacion;
import com.bidbay.models.entity.Usuario;

@Service
public class NotificacionService implements INotificacionService{

	@Autowired
	private INotificacionDao notificacionDao;
	
	@Override
	public Notificacion obtenerNotificacion(Long id) {
		return notificacionDao.obtenerNotificacion(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificacion> findAll() {
		return (List<Notificacion>) notificacionDao.findAll();
	}
	
	@Override
	public void save(Notificacion notificacion) {
		notificacionDao.save(notificacion);
	}

	@Override
	@Transactional
	public void eliminarNotificacion(Long id) {
		notificacionDao.eliminarNotificacion(id);
	}

	@Override
	public void crearNotificacion(String titulo, String notificacion, Long idUsuario, String enlace) {
		notificacionDao.crearNotificacion(titulo, notificacion, idUsuario, enlace);
		
	}
	
	

}
