package com.bidbay.controllers;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IProductoService;

@Controller
@SessionAttributes("carrito")
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
		return "views/carritoView";
	}
	
	@RequestMapping(value = "/form/{id}")
    public String agregar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Producto p = productoService.findOne(id);
		
        Carrito c = new Carrito(null, p, 1);
        carritoService.save(c);
        model.put("carrito", c);
        model.put("titulo", "Agregar al carrito");
        model.put("botonSubmit", "Agregar");
        return "views/carritoView";
    }
	
	/*@RequestMapping(value = "/carrito/agregar/{idItem}", method = RequestMethod.POST)
	public String guardar(@Valid Carrito carrito, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	return "redirect:views/carritoView";
	    }
	    try {
	        carritoService.save(carrito);
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al agregar al carrito: " + e.getMessage());
	        return "redirect:views/carritoView";
	    }
	    return "redirect:views/carritoView";
	}
	
	@RequestMapping(value = "/carrito/agregar/{idItem}", method = RequestMethod.POST)
	public String eliminarDeCarrito(@Valid Carrito carrito, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	    	model.addAttribute("error", "Error al agregar al carrito");
	    	return "redirect:views/carritoView";
	    }
	    try {
	        carritoService.save(carrito);
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al agregar al carrito: " + e.getMessage());
	        return "redirect:views/carritoView";
	    }
	    return "redirect:views/carritoView";
	}*/
	
	
}
