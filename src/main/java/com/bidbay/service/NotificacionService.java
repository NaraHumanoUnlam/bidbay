package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.entity.Notificacion;

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
	

}
