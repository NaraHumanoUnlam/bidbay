package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ICompraDao;
import com.bidbay.models.entity.Compras;

@Service
public class CompraServiceImpl implements IComprasService{

	@Autowired
	private ICompraDao compraDao;

	@Override
	public List<Compras> findAll() {
		return (List<Compras>)compraDao.findAll();
	}

	@Override
	public void save(Compras compra) {
		compraDao.save(compra);
	}
	
	@Override
	public Compras findOne(Long id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		compraDao.deleteById(id);
	}

	
	@Override
	public List<Compras> comprasDelUsuario(Long id_usuario) {
		return compraDao.comprasDelusuario(id_usuario);
	}
	
	
}
