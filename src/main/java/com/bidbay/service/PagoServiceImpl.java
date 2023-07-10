package com.bidbay.service;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICompraDao;
import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.ITicketDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.DetalleCompras;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Ticket;
import com.bidbay.models.entity.Usuario;
import com.bidbay.models.entity.Notificacion;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	private IPagoDao pagoDao;

	@Autowired
	private ICompraDao compraDao;

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ITicketDao ticketDao; 
	
	@Autowired
	private IProductoService productoServicio;

	@Autowired
	private INotificacionDao notificacionDao;

	@Override
	public List<Pago> findAll() {
		// TODO Auto-generated method stub
		return (List<Pago>) pagoDao.findAll();
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

		return pagoDao.findById(idPago).orElse(null);
	}

	@Transactional
	@Override
	public Pago pagarParticular(Pago pagoARealizar, Long idCompra, Long idUsuario) {
		Compras compraAPagar = compraDao.findById(idCompra).orElse(null);
		if (validarPago(pagoARealizar).getAprobado() && compraAPagar != null) {
			save(pagoARealizar);
			compraAPagar.setIdPago(pagoARealizar.getIdPago());
			LocalDate currentDate = LocalDate.now();
			Date fecha = java.sql.Date.valueOf(currentDate);
			compraAPagar.setFecha(fecha);
			compraDao.save(compraAPagar);

			this.mandarNotificacionDeReview(compraAPagar.getDetalles(), idUsuario);
			
			generarTicket(pagoARealizar, compraAPagar, idUsuario);
			
			//var temporal = generarTicket(pagoARealizar, compraAPagar, idUsuario);
			//pagoARealizar.setTicket(temporal); 
			descontarStockProductos(compraAPagar);
			
			notificacionDao.crearNotificacion("Transaccion", pagoARealizar.getMensaje(), idUsuario,"");

		}
		return pagoARealizar;
	}
	
	@Transactional
	private void mandarNotificacionDeReview(List<DetalleCompras> detalleCompra, Long idUsuario) {
		// TODO Auto-generated method stub
		for (DetalleCompras detalle : detalleCompra) {
		    notificacionDao.crearNotificacion("Reseña", "Deja una reseña en tu última compra: " + detalle.getProducto().getNombre() + ".", idUsuario, "/review/dejarReview/" + detalle.getProducto().getId());
		}

	}



	@Transactional
	@Override
	public Pago pagarTotal(Pago pagoARealizar, Long idUsuario) {
		
		if (validarPago(pagoARealizar).getAprobado()) {
			save(pagoARealizar);
			this.pagarComprasDelUsuario(pagoARealizar.getIdPago(), idUsuario);
			
			notificacionDao.crearNotificacion("Transaccion", pagoARealizar.getMensaje(), idUsuario,"");
			
		} else {
			notificacionDao.crearNotificacion("Transaccion", pagoARealizar.getMensaje(), idUsuario,"");

		}

	// marcar mensaje a devolver
	return pagoARealizar;

	}

	private void pagarComprasDelUsuario(Long idPago, Long idUsuario) {
		// TODO Auto-generated method stub
		Double precioAcumulado = 0.0;
		LocalDate currentDate = LocalDate.now();
		Date fecha = java.sql.Date.valueOf(currentDate);
		List<Compras> comprasDelUsuario = compraDao.comprasSinPagarDelusuario(idUsuario);
		for (Compras compra : comprasDelUsuario) {
			compra.setIdPago(idPago);
			
			compra.setFecha(fecha);
			this.mandarNotificacionDeReview(compra.getDetalles(), idUsuario);
			descontarStockProductos(compra);
			precioAcumulado += compra.getMonto();
			compraDao.save(compra);
			
		}
		generarTicketParaTodos(idPago, precioAcumulado,fecha, idUsuario);
		
		
	}

	private Pago validarPago(Pago pagoAGenerar) {
		String regexNumeroT = "^[0-9]{1,16}$";
		String regexCVC = "^[0-9]{1,3}$";
		String regexNombre = "^[a-zA-Z ]+$";
		int validacion = 0;

		if (pagoAGenerar.getNumeroTarjeta().matches(regexNumeroT)) {
			validacion++;

			if (pagoAGenerar.getCvc().matches(regexCVC)) {
				validacion++;

				if (pagoAGenerar.getNombreDeCliente().matches(regexNombre)) {
					validacion++;
					pagoAGenerar.setMensaje("validaciones exitosas, su pago fue aprobado! ");
					pagoAGenerar.setAprobado(true);
					System.out.println(pagoAGenerar.getAprobado());

					String primerosCuatroDigitos = pagoAGenerar.getNumeroTarjeta().substring(0, 4);
					if (primerosCuatroDigitos.equals("4517")) {
						pagoAGenerar.setTipoDeTarjeta("Debito");
					} else {
						pagoAGenerar.setTipoDeTarjeta("Credito");
					}

					return pagoAGenerar;
				} else {
					pagoAGenerar.setMensaje(
							"Pago Rechazado: " + pagoAGenerar.getNombreDeCliente() + " es un nombre invalido");
					return pagoAGenerar;
				}
			} else {
				pagoAGenerar.setMensaje("Pago Rechazado: " + pagoAGenerar.getCvc() + " es una clave invalida");
				return pagoAGenerar;
			}
		} else {
			pagoAGenerar.setMensaje("Pago Rechazado: " + pagoAGenerar.getNumeroTarjeta() + " es una tarjeta invalida");

			return pagoAGenerar;
		}
	}

	private void descontarStockProductos(Compras compraAPagar) {
		List<DetalleCompras>variable = compraAPagar.getDetalles();
		for (DetalleCompras detalleCompras : variable) {
			for (int i = 0; i < detalleCompras.getCantidad(); i++) {
				productoServicio.actualizarStock(1, detalleCompras.getProducto().getId());
			}
		}
	}
	
	@Override
	public void generarTicket(Pago pagoARealizar, Compras compraAPagar, Long idUsuario) {
		
//		List<Producto> listaDeProductos = new ArrayList<Producto>();
//		List<DetalleCompras>listaDetalleCompra = compraAPagar.getDetalles();
//		for (DetalleCompras detalleCompras : listaDetalleCompra) {
//			Producto estoMeDaUnProducto = detalleCompras.getProducto();
//			listaDeProductos.add(estoMeDaUnProducto);
//		},listaDeProductos
		
		Ticket ticket = new Ticket(pagoARealizar.getIdPago(), idUsuario, compraAPagar.getFecha(),compraAPagar.getMonto());
		ticketDao.save(ticket);
		pagoARealizar.setTicket(ticket);
		pagoDao.save(pagoARealizar);	
	}

	@Override
	public void generarTicketParaTodos(Long idPago, Double monto, Date fecha, Long idUsuario) {
	Pago pagoARealizar = pagoDao.findById(idPago).orElse(null);
	Ticket ticket = new Ticket(idPago, idUsuario, fecha ,monto);
	ticketDao.save(ticket);
	pagoARealizar.setTicket(ticket);
	pagoDao.save(pagoARealizar);	
}


}//ultima
