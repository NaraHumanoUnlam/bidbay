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
	private IUsuarioService servicioUsuario;
	private ICarritoService servicioCarrito;
//	private ICompraService compraServicio;
	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoDao.findAll();
	}



	@Override
	public Long generarPago(Carrito carrito ) {
		var usuario = servicioUsuario.findNickById((Long) carrito.getIdUsuario()); //me traigop el usuario nick 
		double precioTotal = servicioCarrito.calcularPrecioTotal((Long) carrito.getIdUsuario()); //traigo el precio total para mostrar

		Pago pagoAGenerar = new Pago(usuario, precioTotal);
		return pagoAGenerar.getIdPago();
	}
	
	
	@Override
	public String pagar(Long idDePago, String mail, String Dni, Long tarjeta,Integer clave, String nombre, String mes, String anio ) {
		
		Pago trxAPagar = findById(idDePago); 
		trxAPagar.guardarDatos(mail, Dni, tarjeta,clave, nombre, mes, anio);
			if (validarPago(trxAPagar)) {	
				trxAPagar.setTicket(generarTicket(trxAPagar,true));
				save(trxAPagar); 
				
			}else {
				trxAPagar.setTicket(generarTicket(trxAPagar,false));
				
			}
			return trxAPagar.getTicket();					
						
		}
		
	private boolean validarPago(Pago pagoAGenerar) {
		int validacion=0;
		//datos de tarjeta hardcodeado
		Long validacionDeNumero = 4545000088887777L;
		Integer clave = 123; 
		String nombreEjemplo = "ejemplo";
			if (pagoAGenerar.getNumeroTarjeta().equals(validacionDeNumero)) {
				
				validacion++;
				if(pagoAGenerar.getCvc().equals(clave)) {
					
					validacion++;
					if(pagoAGenerar.getNombreDeCliente().equals(nombreEjemplo)) {
						
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
	


	private String generarTicket(Pago pagoRealizado, boolean esValido) {
		String retornar;
		if (esValido) { //cargo el ticket de aprovación para mostrar
		 retornar = "ID de pago: " + pagoRealizado.getIdPago().toString()
				+ "/nFecha de compra: " + pagoRealizado.getfechaDeVencimiento() 
				+ "/nUsuario solicitante: " + pagoRealizado.getUsario()
				+ "/nPrecio final de la orden: " + pagoRealizado.getPrecio().toString();
		
		}else {
			
			retornar = "El pago fue rechazado";
		}
		pagoRealizado.setTicket (retornar);
		return retornar;
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

	@Override
	public Pago findById(Long idPago) { //traigo el pago que genere antes
	    return  pagoDao.findById(idPago).orElse(null);
	    }







	
	
}
