package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bidbay.service.ICarritoService;

@Controller
public class CompraController {
	@Autowired
	private ICarritoService carritoService;
	
	@GetMapping("/compra")
	public String comprar(Model model) {
		model.addAttribute("titulo", "Compra");
		model.addAttribute("carrito", carritoService.findAll());
		return "views/compraView";
	}
}
