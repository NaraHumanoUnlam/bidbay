package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICarritoDao;
import com.bidbay.models.entity.Carrito;

@Service
public class CarritoServiceImpl implements ICarritoService{
	
	@Autowired
	private ICarritoDao carritoDao;
	 
	@Override
	public List<Carrito> findAll() {
		// TODO Auto-generated method stub
		return (List<Carrito>)carritoDao.findAll();
	}	

	@Override
	@Transactional
	public void save(Carrito carrito) {
		carritoDao.save(carrito);
	}

	@Override
	@Transactional(readOnly = true)
	public Carrito findOne(Long id) {
		return carritoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		carritoDao.deleteById(id);
	}


}
