package com.bidbay.service;
import java.util.List;


import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Pago;

public interface IPagoService {
	//REVISAR METODOS DE INTERFACE
	public List <Pago>findAll();
	public boolean delete (Long id);

	public Pago findById(Long numeroDeDoc);
	public void save (Pago pago); 
	Pago pagar(Pago pagoARealizar);
//	boolean pagar(Pago pagoARealizar);

	
//	Long generarPago(Carrito carrito);

//	String pagar(Long idDePago, String mail, String Dni, Long tarjeta, Integer clave, String nombre, String mes,
//			String anio);


}
