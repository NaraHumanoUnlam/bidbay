package com.bidbay.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.ICarritoItemDao;
import com.bidbay.models.dao.ICategoriaDao;
import com.bidbay.models.dao.IFavoritosDao;
import com.bidbay.models.dao.IOperacionCV;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;
    
    @Autowired
    private ICategoriaDao categoriaDao;
    
    @Autowired
    private ICarritoItemDao carritoItemDao;
    
    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Autowired
    private IOperacionCV operacionCVDao;
 
    @Autowired
    private IFavoritosDao favoritosDao;
    
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
    	//carritoItemDao.deleteByProductoId(id);
    	
    	productoDao.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteFromView(Long id) {
    	//carritoItemDao.deleteByProductoId(id);
    	
    	productoDao.deleteFromView(id);
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
	
	@Override
	public List<Favoritos>  detalleFavoritosDelUsuario(Long id_usuario) {
		return (List<Favoritos>) favoritosDao.favoritosDelusuario(id_usuario);
	}
	
	@Override
	public Favoritos buscoFavoritoDelUsuario(Long id_usuario , Long id_producto) {
		 for (Favoritos favorito : detalleFavoritosDelUsuario(id_usuario)) {
			 if(favorito.getProducto().getId() == id_producto)
				 return favorito;
		 }
		return null;
	}
	

	public Boolean clickFavoritoDelUsuario(Long id_usuario , Long id_producto) {
		Favoritos favorito = buscoFavoritoDelUsuario(id_usuario ,id_producto);
		if (favorito == null) {
			favorito = new Favoritos();
			favorito.setProducto(findOne(id_producto));
			favorito.setUsuario(usuarioDao.findById(id_usuario).orElse(null));
			favoritosDao.save(favorito);
			return true;
		} else {
			favoritosDao.delete(favorito);
			return false;
		}
	}
	@Transactional
	@Override
	public void descontarStock(Integer cantidad, Long id) {
		productoDao.descontarStock(cantidad, id);
	}
	@Transactional
	@Override
	public void restituirStock(Integer cantidad, Long id) {
		productoDao.restituirStock(cantidad, id);	
	}
	
	@Override
	public List<OperacionCV> findAllComprasVentas() {
		   return (List<OperacionCV>)operacionCVDao.findAll();
	}
	
	@Override
	public Integer cantidadComprasVentas() {
		 Integer sumatoria=0;
		 for (OperacionCV operacion : findAllComprasVentas()) 
			 sumatoria+= operacion.getCantidad();
		return sumatoria;
	}
	
	@Override
	public boolean productoEsVendidoPorUsuario(Long idUsuario, Long idProducto) {
		if(productoDao.productoEsVendidoPorUsuario(idUsuario, idProducto) == 0) {
			return false;
		}
		return true;
	}
	
	@Transactional
	@Override
	public Boolean validarStock(Long idProducto,Integer cantidad) {
		Boolean validacionDeStock = false;
		
		Integer stockPermitido = productoDao.traerStockDisponible(idProducto);
		
		if(stockPermitido >= cantidad) {
			validacionDeStock = true; 
		}else {
			validacionDeStock = false;
		}
		
		return validacionDeStock;
	}




}
