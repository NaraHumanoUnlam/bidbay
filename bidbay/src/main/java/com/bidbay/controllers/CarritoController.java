package com.bidbay.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IProductoService;

@Controller
//@SessionAttributes("carrito")
@RequestMapping("/carrito")
public class CarritoController {
	
	@Autowired
	private ICarritoService carritoService;
	
	@Autowired
	private IProductoService productoService; 
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de carrito");
		model.addAttribute("carrito", carritoService.findAll());
		model.addAttribute("precioTotal", calcularPrecioTotal());
		return "views/carritoView";
	}
	
	@RequestMapping(value = "/form/{id}")
    public String agregar(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
		Producto p = productoService.findOne(id);
		List<Carrito> carritosActuales = carritoService.findAll();
		for(Carrito carritoEncontrado : carritosActuales) {
			Producto productoEncontrado = carritoEncontrado.getProducto();
			if(productoEncontrado.equals(p)) {
				Integer stock = (Integer) carritoEncontrado.getStock();
				stock++;
				cambiarStockDelCarrito(carritoEncontrado, stock, redirectAttributes);
		        return "redirect:/carrito/listar";
			}
		}
        Carrito c = new Carrito(null, p, 1);
        carritoService.save(c);
        redirectAttributes.addFlashAttribute("mensaje", "Producto agregado correctamente al carrito");
        return "redirect:/carrito/listar";
    }
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
	public String editar(@PathVariable(value = "id") Long id, Model model, @RequestParam("cantidadProductos") int stock, RedirectAttributes redirectAttributes) {
	    /*SI ESTOY LEYENDO ESTO ES PORQYUE VOLVI DE LA FACULTAD:
	     * TODO: -AGREGAR UN METODO PARA CALCULAR PRECIO TOTAAAAAAAL DE TODOS LOS CARRITOS (NO SEGUN USUARIO PORQUE RIP SECURITY)
	     * -CAMBIAR LA FORMA DE BAJAR Y AGREGAR STOCK A VER QUE ONDA*/
		Carrito carrito = carritoService.findOne(id);
	    if (carrito == null) {
	        return "redirect:/carrito/listar";
	    }
	    
	    if(stock == 0) {
	    	redirectAttributes.addFlashAttribute("mensajeError", "El stock debe ser mayor a 0");
	    	return "redirect:/carrito/listar";
	    }
	    
	    cambiarStockDelCarrito(carrito, stock, redirectAttributes);

	    return "redirect:/carrito/listar";
	}
	
	private void cambiarStockDelCarrito(Carrito carrito, Integer stockNuevo, RedirectAttributes redirectAttributes){
		Producto producto = carrito.getProducto();
		if(producto.getStock() < stockNuevo) {
			stockNuevo = producto.getStock();
			redirectAttributes.addFlashAttribute("mensajeError", "Stock insuficiente, máximo " + stockNuevo.toString());
		} else {
			redirectAttributes.addFlashAttribute("mensajeExito", "Stock actualizado correctamente");
		}
		carrito.setStock(stockNuevo);
		carritoService.save(carrito);
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
	    Carrito carritoExistente = carritoService.findOne(id);

	    if (carritoExistente == null) {
	        return "redirect:/carrito/listar";
	    }

	    carritoService.delete(id);
	    redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado correctamente del carrito");

	    return "redirect:/carrito/listar";
	}
	
	private Double calcularPrecioTotal() {
		List<Carrito> carritos= carritoService.findAll();
	    Double precioTotal = 0.0;
	    
	    for (Carrito carrito : carritos) {
	        Producto producto = carrito.getProducto();
	        Integer stock = (Integer) carrito.getStock();
	        Double precio = producto.getPrecio();
	        
	        precioTotal += precio * stock;
	    }
	    
	    return precioTotal;
	}
	
}
