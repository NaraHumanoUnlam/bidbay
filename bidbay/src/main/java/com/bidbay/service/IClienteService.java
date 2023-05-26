package com.bidbay.service;
import java.util.List;
import com.bidbay.models.entity.Cliente;

public interface IClienteService {
	
	public List <Cliente>findAll();
	public String validarMetodoDePago (String metodoDePago);
	public String pagarCompra (Double precio); 
	public boolean delete (Long id);
	public Cliente findByDni(String numeroDeDoc);

}
