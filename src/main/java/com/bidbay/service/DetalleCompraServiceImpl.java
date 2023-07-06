package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.IDetalleCompra;
import com.bidbay.models.entity.DetalleCompras;

@Service
public class DetalleCompraServiceImpl implements IDetalleCompraService {
	@Autowired
	private IDetalleCompra detalleCompraDao;

	@Override
	public List<DetalleCompras> findAll() {
		return (List<DetalleCompras>) detalleCompraDao.findAll();
	}

	@Override
	public void save(DetalleCompras detallecompra) {
		detalleCompraDao.save(detallecompra);
	}

	@Override
	public List<DetalleCompras> listarDetallePorId(Long compra_id) {
		return detalleCompraDao.detallesCompras(compra_id);
	}

}
