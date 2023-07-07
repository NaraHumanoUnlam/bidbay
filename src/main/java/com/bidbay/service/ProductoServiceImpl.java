package com.bidbay.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICategoriaDao;
import com.bidbay.models.dao.IOperacionCV;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.*;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;
    
    @Autowired
    private ICategoriaDao categoriaDao;
    
    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private IOperacionCV operacionCVDao;
 
    
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		   return (List<Producto>)productoDao.findAll();
	}

    @Override
    @Transactional
    public void save(Producto producto) {
    	productoDao.save(producto);
    }

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	
    @Override
    @Transactional
    public void delete(Long id) {
    	productoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByName(String name) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : findAll()) {
            if (p.getNombre().toLowerCase().contains(name.toLowerCase())) {
            	productosEncontrados.add(p);
            }
        }
        return productosEncontrados;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNameDelUsuario(String name, Long id_usuario) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : productoDelUsuario(id_usuario)) {
            if (p.getNombre().toLowerCase().contains(name.toLowerCase())) {
            	productosEncontrados.add(p);
            }
        }
        return productosEncontrados;
    }

	@Override
	public List<Producto> orderList(String orden) {
		List<Producto> listaOrdenada = findAll();
        if (orden.equalsIgnoreCase("asc")) {
        	Collections.sort(listaOrdenada, Comparator.comparing(Producto::getPrecio));
        } else if (orden.equalsIgnoreCase("desc")) {
        	Collections.sort(listaOrdenada, Comparator.comparing(Producto::getPrecio).reversed());
        }
        return listaOrdenada;
	}
	
	@Override
	public List<Producto> orderListDelUsuario(String orden, Long id_usuario) {
		List<Producto> listaOrdenada = productoDelUsuario(id_usuario);
        if (orden.equalsIgnoreCase("asc")) {
        	Collections.sort(listaOrdenada, Comparator.comparing(Producto::getPrecio));
        } else if (orden.equalsIgnoreCase("desc")) {
        	Collections.sort(listaOrdenada, Comparator.comparing(Producto::getPrecio).reversed());
        }
        return listaOrdenada;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByCategoriaId(Long id) {
	    List<Producto> productosEncontrados = new ArrayList<>();
	    Categoria categoria = categoriaDao.findById(id).orElse(null);
	    if (categoria != null) {
	        productosEncontrados = categoria.getProductos();
	    }
	    return productosEncontrados;
	}
	
	

	@Override
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, HttpSession session) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.findById((Long) session.getAttribute("idUsuario")).orElse(null);
        Producto producto = productoDao.findById(idProducto).orElse(null);
        
        if (usuario != null && producto != null) {
            producto.dejarReview(usuario, mensaje, puntaje);
            productoDao.save(producto);
            
            Notificacion notificacion = new Notificacion("Reseña","¡Bienvenido!", usuario);
            usuario.agregarNotificacion(notificacion);
            usuarioDao.save(usuario);
            } 
        }
	
	@Override
	public List<Producto> productoDelUsuario(Long id_usuario) {
		return productoDao.detallesProducto(id_usuario);
	}
	
	@Override
	public List<OperacionCV> detalleComprasDelUsuario(Long id_usuario) {
		return operacionCVDao.detalleCompras(id_usuario);
	}
	
	@Override
	public List<OperacionCV> detalleVentasDelUsuario(Long id_usuario) {
		return operacionCVDao.detalleVentas(id_usuario);
	}
	
	
	@Override
	public Integer comprasDelUsuario(Long id_usuario) {
		 Integer sumatoria=0;
		 for (OperacionCV operacion : detalleComprasDelUsuario(id_usuario)) 
			 sumatoria+= operacion.getCantidad();
		return sumatoria;
	}
	
	
	@Override
	public Integer ventasDelUsuario(Long id_usuario) {
		 Integer sumatoria=0;
		 for (OperacionCV operacion : detalleVentasDelUsuario(id_usuario)) 
			 sumatoria+= operacion.getCantidad();
		return sumatoria;
	}

}
