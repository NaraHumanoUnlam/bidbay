package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.IProductoService;

import jakarta.validation.Valid;

@Controller
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value="producto", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		return "views/productoView";
	}
	
	@RequestMapping(value="producto/agregar", method = RequestMethod.GET)
	public String crear(Map <String, Object> model) {
		model.put("titulo", "Formulario de Producto");
		Producto producto = new Producto();
		model.put("producto", producto);
		return "views/agregarProducto";
	}
	
	@RequestMapping(value = "producto/agregar", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("titulo", "Formulario de Producto");
	        return "views/agregarProducto";
	    }

	    try {
	        productoService.save(producto);
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al guardar el producto: " + e.getMessage());
	        return "views/agregarProducto";
	    }

	    return "redirect:/producto";
	}
	
	
}
