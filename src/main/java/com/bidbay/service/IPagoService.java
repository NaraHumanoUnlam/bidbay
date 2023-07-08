package com.bidbay.service;
import java.util.List;


import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Pago;

public interface IPagoService {
	public List <Pago>findAll();
	public boolean delete (Long id);

	public Pago findById(Long numeroDeDoc);
	public void save (Pago pago);
	public Pago pagarTotal(Pago pagoARealizar, Long idUsuario);
	public Pago pagarParticular(Pago pagoARealizar, Long idCompra, Long idUsuario);

	public void generarTicket(Long idCompra, Double Precio, String nickuser ); 
	

}
