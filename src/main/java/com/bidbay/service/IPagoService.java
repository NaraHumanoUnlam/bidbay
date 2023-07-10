package com.bidbay.service;
import java.sql.Date;
import java.util.List;


import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Ticket;

public interface IPagoService {
	public List <Pago>findAll();
	public boolean delete (Long id);

	public Pago findById(Long numeroDeDoc);
	public void save (Pago pago);
	public Pago pagarTotal(Pago pagoARealizar, Long idUsuario);
	public Pago pagarParticular(Pago pagoARealizar, Long idCompra, Long idUsuario);

	public void generarTicket(Pago pagoARealizar, Compras compraAPagar, Long idUsuario);
	void generarTicketParaTodos(Long idPago, Double monto, Date fecha, Long idUsuario); 
	

}
