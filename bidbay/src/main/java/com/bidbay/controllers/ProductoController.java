package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Producto;
import com.bidbay.service.IProductoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("producto")
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		return "views/productoView";
	}
	
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String crear(Map <String, Object> model) {

		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Formulario de Producto");
		model.put("botonSubmit", "Crear");
		return "views/productoForm";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("titulo", "Formulario de Producto");
	        return "views/productoForm";
	    }
	    try {
	        productoService.save(producto);
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al guardar el producto: " + e.getMessage());
	        return "views/productoForm";
	    }
	    return "redirect:/producto/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Producto p = null;
        if (id > 0) {
            p = productoService.findOne(id);
        } else {
            return "views/productoForm";
        }
        model.put("producto", p);
        model.put("titulo", "Editar Producto");
        model.put("botonSubmit", "Editar");
        return "views/productoForm";
    }
	
	  @RequestMapping(value = "/delete/{id}")
	    public String eliminar(@PathVariable(value = "id") Long id) {
	        if (id > 0) {
	        	productoService.delete(id);
	        }
		    return "redirect:/producto/listar";
	    }
	
}
