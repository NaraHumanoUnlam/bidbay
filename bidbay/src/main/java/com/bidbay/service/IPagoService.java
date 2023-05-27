package com.bidbay.service;
import java.util.List;
import com.bidbay.models.entity.Pago;

public interface IPagoService {
	//REVISAR METODOS DE INTERFACE
	public List <Pago>findAll();
	//public String validarMetodoDePago (String metodoDePago);
	public String pagarCompra (Double precio); 
	public boolean delete (Long id);
	public Pago findByDni(String numeroDeDoc);

}
