package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bidbay.service.IProductoService;

@Controller
public class HomeController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value = { "/", "/index" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Bidbay");
		model.addAttribute("productos", productoService.findAll());
		return "index";
	}
	
	@GetMapping(value = { "/home" })
	public String home(Model model) {
		model.addAttribute("titulo", "Bidbay");
		model.addAttribute("productos", productoService.findAll());
		return "home";
	}

	
	
	
	
}
