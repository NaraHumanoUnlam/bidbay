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

	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>)pagoDao.findAll();
	}



	@Override //lo hacemos con Nara
	public String pagarCompra(Compra compraRealizada) {

		boolean respuesta = false; 
		String Ticket ="";
		//declaramaos un servicio de compra y tipado compra
		Pago pagoAGenerar = new Pago();
		Double PrecioTotal = (compraRealizada.getCarrito().getProducto().getPrecio() * compraRealizada.getCarrito().getCantidadProductos());
		
		pagoAGenerar.setPrecio(PrecioTotal);//le mando el precio que debe pagarse en total 
		pagoAGenerar.setDNI(null); // traer de html
		pagoAGenerar.setMailCliente(null);
		pagoAGenerar.setMesVencimiento(null);
		pagoAGenerar.setAnio(null);
		pagoAGenerar.transcribirFecha(null, null); 
		pagoAGenerar.setUsario(servicio.findNickById(compraRealizada.getCarrito().getIdUsuario()));
		
		pagoAGenerar.setNombreDeCliente(null);
		pagoAGenerar.setNumeroTarjeta(null);
		pagoAGenerar.setCvc(null);
		
		if (validarPago(pagoAGenerar)) {
			Ticket = generarTicket(pagoAGenerar.getIdPago(),
					pagoAGenerar.getPrecio(),
					pagoAGenerar.getUsario(),
					compraRealizada.getCodigo(),
					compraRealizada.getFecha(),
					compraRealizada.getCarrito().getCantidadProductos(),
					compraRealizada.getCarrito().getProducto().getDescripcion());
			save(pagoAGenerar); 
			respuesta = true; 
			}else {
				Ticket = "Su compra no pudo ser realizada"; 
			}
		return Ticket;
	}

	private String generarTicket(Long idPago, Double precio, String usuario, String codigoCompra
			, Date fechaDeCompra, Integer cantidad, String descripcionProducto) {
		String retornar = "ID de pago: " + idPago.toString()
				+ "/nCodigo de compra: " + codigoCompra 
				+ "/nFecha de compra: " + fechaDeCompra.toString() 
				+ "/nUsuario solicitante: " + usuario
				+ "/nDescripcion del producto: " + descripcionProducto
				+ "/nCantidad de producto comprado: " + cantidad.toString()
				+ "/nPrecio final de la orden: " + precio.toString();
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
