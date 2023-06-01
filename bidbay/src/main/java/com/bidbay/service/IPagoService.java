package com.bidbay.service;
import java.util.List;

import com.bidbay.models.entity.Compra;
import com.bidbay.models.entity.Pago;

public interface IPagoService {
	//REVISAR METODOS DE INTERFACE
	public List <Pago>findAll();
	//public String validarMetodoDePago (String metodoDePago);
	public String pagarCompra (Double precio); 
	public String pagarCompra(Compra datos);
	public boolean delete (Long id);
	public Pago findByDni(String numeroDeDoc);
	public Pago findById(Long numeroDeDoc);
	public void save (Pago pago); 
	//private boolean validarPago(Pago pagoAGenerar);

}
