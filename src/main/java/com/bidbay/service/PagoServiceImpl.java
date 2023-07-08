package com.bidbay.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICompraDao;
import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	private IPagoDao pagoDao;

	@Autowired
	private ICompraDao compraDao;

	@Autowired
	private IUsuarioDao usuarioDao;

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

			notificacionDao.crearNotificacion("Transaccion", "Tu pago fue aprobado", idUsuario);
			pagoARealizar.setAprobado(true);
		} else {
			pagoARealizar.setAprobado(false);

		}
		return pagoARealizar;
	}

	@Transactional
	@Override
	public Pago pagarTotal(Pago pagoARealizar, Long idUsuario) {
		if (validarPago(pagoARealizar).getAprobado()) {
			save(pagoARealizar);
			this.pagarComprasDelUsuario(pagoARealizar.getIdPago(), idUsuario);

			notificacionDao.crearNotificacion("Transaccion", "Tu pago fue aprobado", idUsuario);
			pagoARealizar.setAprobado(true);
		} else {
			notificacionDao.crearNotificacion("Transaccion", "Tu pago fue denegado", idUsuario);
			pagoARealizar.setAprobado(false);
		}

	// marcar mensaje a devolver

	return pagoARealizar;

	}

	private void pagarComprasDelUsuario(Long idPago, Long idUsuario) {
		// TODO Auto-generated method stub
		List<Compras> comprasDelUsuario = compraDao.comprasDelusuario(idUsuario);
		for (Compras compra : comprasDelUsuario) {
			compra.setIdPago(idPago);
			LocalDate currentDate = LocalDate.now();
			Date fecha = java.sql.Date.valueOf(currentDate);
			compra.setFecha(fecha);
			compraDao.save(compra);
		}
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

	@Override
	public void generarTicket(Long idCompra, Double Precio, String nickuser) {
		// TODO Auto-generated method stub

	}

}
