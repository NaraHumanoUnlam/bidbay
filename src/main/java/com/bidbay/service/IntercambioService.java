package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.IIntercambioDao;
import com.bidbay.models.entity.Intercambio;

@Service
public class IntercambioService implements IIntercambioService{

	@Autowired
	private IIntercambioDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Intercambio> listar() {
		return dao.listar();
	}

	
}
