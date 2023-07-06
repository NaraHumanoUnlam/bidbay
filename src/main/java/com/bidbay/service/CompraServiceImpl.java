package com.bidbay.service;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ICarritoDao;
import com.bidbay.models.dao.ICarritoItemDao;
import com.bidbay.models.dao.ICompraDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.DetalleCompras;

@Service
public class CompraServiceImpl implements IComprasService{

	@Autowired
	private ICompraDao compraDao;
	
	@Autowired
	private ICarritoDao carritoDao;
	
	@Autowired
	private ICarritoItemDao carritoItemDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public List<Compras> findAll() {
		return (List<Compras>)compraDao.findAll();
	}

	@Override
	public void save(Compras compra) {
		compraDao.save(compra);
	}
	
	@Override
	public Compras findOne(Long id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		compraDao.deleteById(id);
	}

	
	@Override
	public List<Compras> comprasDelUsuario(Long id_usuario) {
		return compraDao.comprasDelusuario(id_usuario);
	}
	
	@Override
	public void crearCompra(Long idUsuario){
		Compras compras = new Compras(idUsuario);
		
		Carrito carrito = carritoDao.findCarritoFromUser(idUsuario);
		List<CarritoItem> items = carritoItemDao.findCarritoItemsFromCarrito(carrito.getId());
		
		List<DetalleCompras> detalles = new ArrayList<DetalleCompras>();
		

		for(CarritoItem carritoItem : items) {
			DetalleCompras detalle = new DetalleCompras(carritoItem.getProducto(), compras);
			detalle.setCantidad(carritoItem.getCantidadProductos());
			detalle.setPrecioCompra(carritoItem.getProducto().getPrecio());
			detalles.add(detalle);
			carritoItemDao.delete(carritoItem);
		}
		
		compras.setDetalles(detalles);
		compraDao.save(compras);
	}

	@Override
	public Double calcularMontoTotalDeCompras(Long id) {
		// TODO Auto-generated method stub
		List<Compras> comprasDelUsuario = compraDao.comprasDelusuario(id);
		Double montoTotal = 0.0;
	    
	    for (Compras compra : comprasDelUsuario) {
	        List<DetalleCompras> detallesCompra = compra.getDetalles();
	        
	        for (DetalleCompras detalleCompra : detallesCompra) {
	            montoTotal += detalleCompra.getPrecioCompra();
	        }
	    }
	    
	    return montoTotal;
	}

	@Override
	public void actualizarFechayPago(Date fecha, Long idPago, Long idCompra) {
		
		compraDao.actualizarFechaYidPago(fecha, idPago, idCompra);
		
	}
	
	
}
