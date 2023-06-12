package com.bidbay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ICarritoItemDao;
import com.bidbay.models.entity.CarritoItem;

@Service
public class CarritoItemServiceImpl implements ICarritoItemService{
	@Autowired
	private ICarritoItemDao carritoItemDao;
	

	@Override
	public void delete(CarritoItem carritoItem) {
		// TODO Auto-generated method stub
		carritoItemDao.delete(carritoItem);
	}

}
