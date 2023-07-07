package com.bidbay.service;

import java.sql.Date;
import java.util.List;

import com.bidbay.models.entity.Compras;

public interface IComprasService {

	public List<Compras> findAll();

	public void save(Compras compra);

	public Compras findOne(Long id);

	public void delete(Long id);
	
	public List<Compras> comprasDelUsuario(Long id_usuario);
	
	public void crearCompra(Long idUsuario);

	public Double calcularMontoTotalDeCompras(Long id);
	
	public void actualizarFechayPago(Date fecha, Long idPago, Long idCompra);


}
