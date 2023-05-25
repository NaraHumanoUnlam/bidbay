package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.bidbay.models.entity.Compra;

public class CompraServiceImpl implements ICompraService{

	@Autowired
	private ICompraService compraDao;

	@Override
	public List<Compra> findAll() {
		
		return (List<Compra>)compraDao.findAll();
	}

	@Override
	public void save(Compra compra) {
		compraDao.save(compra);
		
	}

	@Override
	public Compra findOne(Long id) {
		
		return compraDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		compraDao.delete(id);
	}

	
}
