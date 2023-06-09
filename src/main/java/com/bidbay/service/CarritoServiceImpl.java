package com.bidbay.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.models.dao.ICarritoDao;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;
import com.bidbay.models.entity.Producto;

@Service
public class CarritoServiceImpl implements ICarritoService{
	
	@Autowired
	private ICarritoDao carritoDao;
	
	@Autowired
    private IProductoService productoService;
	
	@Autowired
    private IProductoDao productoDao;
	
	@Autowired
	private ICarritoItemService carritoItemService;
	 
	@Override
	public List<Carrito> findAll() {
		// TODO Auto-generated method stub
		return (List<Carrito>)carritoDao.findAll();
	}	

	@Override
	@Transactional
	public void save(Carrito carrito) {
		carritoDao.save(carrito);
	}

	@Override
	@Transactional(readOnly = true)
	public Carrito findOne(Long id) {
		return carritoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		carritoDao.deleteById(id);
	}

	@Override
	@Transactional
	public CarritoItem findCarritoItemById(Long id, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		List<CarritoItem> itemsInCart = carrito.getCarritoItems();
		for(CarritoItem item : itemsInCart) {
			if(item.getIdItem().equals(id)) {
				return item;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteCarritoItem(CarritoItem carritoItem, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		carrito.removeCarritoItem(carritoItem);
		carritoItemService.delete(carritoItem);
		carritoDao.save(carrito);
	}

	@Override
	@Transactional
	public void saveCarritoItem(CarritoItem carritoItem, Long carritoId) {
		// TODO Auto-generated method stub
		Carrito carrito = findOne(carritoId);
		carrito.addCarritoItem(carritoItem);
		carritoDao.save(carrito);
	}

	@Override
	@Transactional
	public Carrito findOneByUserID(Long idUser) {
		// TODO Auto-generated method stub
		List<Carrito> carritos = findAll();
		for(Carrito carrito : carritos) {
			if(carrito.getIdUsuario().equals(idUser)) {
				return carrito;
			}
		}
		//Si no encontro, es porque no existe
		Carrito carrito = new Carrito(idUser);
        save(carrito);
		return carrito;
	}

	@Override
	public void addProductToCarrito(Long idUsuario, Long idProducto, RedirectAttributes redirectAttributes) {
		// TODO Auto-generated method stub
		Producto producto = productoService.findOne(idProducto);
	    if (producto != null && productoService.productoEsVendidoPorUsuario(idUsuario, idProducto) == false) {
	        List<Carrito> carritosActuales = findAll();

	        boolean carritoEncontrado = false;

	        for (Carrito carrito : carritosActuales) {
	            if (carrito.getIdUsuario().equals(idUsuario)) {
	                carritoEncontrado = true;

	                List<CarritoItem> carritoItems = carrito.getCarritoItems();
	                boolean productoEncontrado = false;

	                for (CarritoItem item : carritoItems) {
	                    if (item.getProducto().equals(producto)) {
	                        Integer stock = item.getStock();
	                        stock++;
	                        changeCarritoItemStock(item, carrito, stock, redirectAttributes);
	                        productoEncontrado = true;
	                        break;
	                    }
	                }

	                if (!productoEncontrado) {
	                    CarritoItem nuevoItem = new CarritoItem(producto, 1);
	                    carrito.addCarritoItem(nuevoItem);
	                }

	                save(carrito);
	                redirectAttributes.addFlashAttribute("mensaje", "Producto agregado correctamente al carrito");
	                break;
	            }
	        }

	        if (!carritoEncontrado) {
	            Carrito carritoNuevo = new Carrito(idUsuario);
	            CarritoItem nuevoItem = new CarritoItem(producto, 1);
	            carritoNuevo.addCarritoItem(nuevoItem);
	            save(carritoNuevo);
	            redirectAttributes.addFlashAttribute("mensaje", "Producto agregado correctamente al carrito");
	        }
	    }
	}

	@Override
	public void editCarritoItem(Long idUsuario, Long id, Integer stock, RedirectAttributes redirectAttributes) {
		// TODO Auto-generated method stub
		Carrito carrito = findOneByUserID(idUsuario);
    	CarritoItem carritoItem = findCarritoItemById(id, carrito.getId());
        if (stock == 0) {
            redirectAttributes.addFlashAttribute("mensajeError", "El stock debe ser mayor a 0");
        }
        if(carritoItem != null && stock > 0) {
        	changeCarritoItemStock(carritoItem, carrito, stock, redirectAttributes);
        }
        
	}

	@Override
	public void deleteCarritoItem(Long idUsuario, Long id, RedirectAttributes redirectAttributes) {
		// TODO Auto-generated method stub
		Carrito carrito = findOneByUserID(idUsuario);
    	CarritoItem carritoItem = findCarritoItemById(id, carrito.getId());
        if(carritoItem != null) {
        	deleteCarritoItem(carritoItem, carrito.getId());
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado correctamente del carrito");
        }
	}

	@Override
	public void changeCarritoItemStock(CarritoItem carritoItem, Carrito carrito, Integer stock,
			RedirectAttributes redirectAttributes) {
		// TODO Auto-generated method stub
		Producto producto = carritoItem.getProducto();
        if (producto.getStock() < stock) {
            stock = producto.getStock();
            redirectAttributes.addFlashAttribute("mensajeError", "Stock insuficiente, máximo " + stock.toString());
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito", "Stock actualizado correctamente");
        }
        carritoItem.setStock(stock);
        saveCarritoItem(carritoItem, carrito.getId());
	}

	@Override
	public double calcularPrecioTotal(Long idUsuario) {
		// TODO Auto-generated method stub
        Double precioTotal = 0.0;
        List<CarritoItem> carritoDelUser = findOneByUserID(idUsuario).getCarritoItems();

        for(CarritoItem carritoItem : carritoDelUser) {
        	Producto producto = carritoItem.getProducto();
            Integer stock = carritoItem.getStock();
            Double precio = producto.getPrecio();

            precioTotal += precio * stock;
        }
        return   Math.round(precioTotal * 100.0) / 100.0 ;
	}


}