package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.IClienteDao;
import com.bidbay.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>)clienteDao.findAll();
	}

	@Override
	public String validarMetodoDePago(String metodoDePago) {
		return null;
	}

	@Override //lo hacemos con Nara
	public String pagarCompra(Double precio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		clienteDao.deleteById(id);  
		boolean respuesta = clienteDao.existsById(id);
		return respuesta; //aca se comprueba si el cliente fue eliminado
	}

	@Override
	public Cliente findByDni(String numeroDeDoc) {
		List <Cliente> listaClientes = (List<Cliente>) clienteDao.findAll();
		for (Cliente cliente : listaClientes) {
			if (cliente.getDNI().equals(numeroDeDoc)) {
				return cliente;
			}
		}
		
		return null;
	}


	

}
