package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICarritoDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;

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

	@Override
	@Transactional
	public CarritoItem findCarritoItemById(Long id, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		List<CarritoItem> itemsInCart = carrito.getCarritoItems();
		for(CarritoItem item : itemsInCart) {
			if(item.getIdItem().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteCarritoItem(CarritoItem carritoItem, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		carrito.removeCarritoItem(carritoItem);
		carritoDao.save(carrito);
	}

	@Override
	@Transactional
	public void saveCarritoItem(CarritoItem carritoItem, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		carrito.addCarritoItem(carritoItem);
		carritoDao.save(carrito);
	}

	@Override
	@Transactional
	public Carrito findOneByUserID(Long idUser) {
		// TODO Auto-generated method stub
		List<Carrito> carritos = findAll();
		for(Carrito carrito : carritos) {
			if(carrito.getIdUsuario().equals(idUser)) {
				return carrito;
			}
		}
		return null;
	}


}