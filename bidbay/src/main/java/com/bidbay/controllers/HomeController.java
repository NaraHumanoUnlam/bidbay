package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.service.IProductoService;

@Controller
public class HomeController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value = { "/", "/home" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Bidbay");
		model.addAttribute("productos", productoService.findAll());
		
		return "index";
	}
	
}
