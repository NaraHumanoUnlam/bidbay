package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.DetalleCompras;

public interface IDetalleCompraService {
	public List<DetalleCompras> findAll();

	public void save(DetalleCompras detallecompra);

	public List<DetalleCompras> listarDetallePorId(Long compra_id);
}
