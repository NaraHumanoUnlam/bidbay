package com.bidbay.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bidbay.models.dao.INotificaionDao;
import com.bidbay.models.entity.Notificacion;

public class NotificacionService implements INotificacionService{

	@Autowired
	private INotificaionDao notificacionDao;
	@Override
	public Notificacion obtenerNotificacion(Long id) {
		return notificacionDao.obtenerNotificacion(id);
	}

}
