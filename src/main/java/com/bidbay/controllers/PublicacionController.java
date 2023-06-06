package com.bidbay.controllers;

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
public class PublicacionController {

	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value = "/publicacion/listar", method = RequestMethod.GET)
	public String listarPubliaciones(Model model) {
		//aca van a estar todas las publicaciones hechas igual que el index :P pero con diferente vista
		model.addAttribute("titulo", "Publicaciones de productos");
		model.addAttribute("productos", productoService.findAll());
		return "views/publicacionView";
	}
	
	@RequestMapping(value = "/publicacion/crear", method = RequestMethod.POST)
	public String crearPublicacion(@Valid Producto producto, BindingResult result, Model model) {
		//aca va a estar el mercado libre trucho :B
		productoService.save(producto);
		model.addAttribute("titulo", "Crear Publicacion");
		model.addAttribute("producto");
		return "views/crearPublicacionView";
	}
}
