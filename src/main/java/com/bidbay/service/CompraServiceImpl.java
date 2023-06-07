package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ICompraDao;
import com.bidbay.models.entity.Compra;

@Service
public class CompraServiceImpl implements ICompraService{

	@Autowired
	private ICompraDao compraDao;

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
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		compraDao.deleteById(id);
	}

	
	
}
