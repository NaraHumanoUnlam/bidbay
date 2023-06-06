package com.bidbay.service;

import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.entity.Compra;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;

@Service
public class PagoServiceImpl implements IPagoService {
	
	@Autowired
	private IPagoDao pagoDao;
	private IUsuarioService servicio;
	private ICompraService compraServicio;
	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoDao.findAll();
	}



	@Override //lo hacemos con Nara
	public String pagarCompra(Pago aRealizar ) {

		boolean respuesta = false; 
		String Ticket ="";
		//declaramaos un servicio de compra y tipado compra
		Pago pagoAGenerar = new Pago();
		
	//	Double PrecioTotal = (compraRealizada.getCarrito().getProducto().getPrecio() * compraRealizada.getCarrito().getCantidadProductos());
		
		//pagoAGenerar.setPrecio(PrecioTotal);//le mando el precio que debe pagarse en total 
		
		
		
		if (validarPago(pagoAGenerar)) {
			
			//Ticket = generarTicket(pagoAGenerar); //compraRealizada
			
			save(pagoAGenerar); 
			
			respuesta = true; 
			}else {
				Ticket = "Su compra no pudo ser realizada"; 
			}
		return Ticket;
	}

	private String generarTicket(Pago pagoRealizado, Compra compraRealizada) {
		
		String retornar = "ID de pago: " + pagoRealizado.getIdPago().toString()
				+ "/nCodigo de compra: " + compraRealizada.getCodigo()
				+ "/nFecha de compra: " + pagoRealizado.getfechaDeVencimiento() 
				+ "/nUsuario solicitante: " + pagoRealizado.getUsario()
				+ "/nDescripcion del producto: " + compraRealizada.getCarrito().getProducto().getDescripcion()
				+ "/nCantidad de producto comprado: " + compraRealizada.getCarrito().getCantidadProductos().toString()
				+ "/nPrecio final de la orden: " + pagoRealizado.getPrecio().toString();
		return retornar;
	}
	
	private boolean validarPago(Pago pagoAGenerar) {

	return true;
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
	public Pago findById(Long numeroDeDoc) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String pagarCompra(Double precio) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
