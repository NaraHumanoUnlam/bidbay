package com.bidbay.service;

import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compra;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	private IPagoDao pagoDao;


	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Pago pago) {
		pagoDao.save(pago);
	}


	@Override
	public boolean delete(Long id) {
		pagoDao.deleteById(id);  
		boolean respuesta = pagoDao.existsById(id);
		return respuesta;
	}

	@Override
	public Pago findById(Long idPago) {

		return  pagoDao.findById(idPago).orElse(null);
	}


	@Override
	public Pago pagar(Pago pagoARealizar ) {

		if (validarPago(pagoARealizar)) {	
			save(pagoARealizar);
			pagoARealizar.setAprobado(true); 
		}else {
			pagoARealizar.setAprobado(false);
		}
		return pagoARealizar;
	}

	private boolean validarPago(Pago pagoAGenerar) {
		int validacion=0;
		//datos de tarjeta hardcodeado
		if (pagoAGenerar.getNumeroTarjeta().equals(pagoAGenerar.getNumeroTarjeta())) {
			//traer datos  avalidar, 5 tarjetas o mp		
			validacion++;
			if(pagoAGenerar.getCvc().equals(pagoAGenerar.getCvc())) {

				validacion++;
				if(pagoAGenerar.getNombreDeCliente().equals("otro")) {

					validacion++;
					System.out.println("validaciones exitosas: " + validacion);
					return true;
				}else {
					System.out.println(pagoAGenerar.getNombreDeCliente() + " es un nombre invalido");
					return false;
				}
			}
			else {
				System.out.println(pagoAGenerar.getCvc() + " es una clave invalida");
				return false;
			}
		}
		else {
			System.out.println(pagoAGenerar.getNumeroTarjeta() + " es una tarjeta invalida");
			return false;
		}
	}


}
