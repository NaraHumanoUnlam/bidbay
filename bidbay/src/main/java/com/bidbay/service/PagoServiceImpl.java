package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.entity.Pago;

@Service
public class PagoServiceImpl implements IPagoService {
	
	@Autowired
	private IPagoDao pagoDao;

	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoDao.findAll();
	}



	@Override //lo hacemos con Nara
	public String pagarCompra(Double precio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		pagoDao.deleteById(id);  
		boolean respuesta = pagoDao.existsById(id);
		return respuesta; //aca se comprueba si el cliente fue eliminado
	}

	@Override
	public Pago findByDni(String numeroDeDoc) {
		List <Pago> listaPago = (List<Pago>) pagoDao.findAll();
		for (Pago pago : listaPago) {
			if (pago.getDNI().equals(numeroDeDoc)) {
				return pago;
			}
		}
		
		return null;
	}


	

}
