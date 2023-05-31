package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;

import jakarta.validation.Valid;

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
	
	@GetMapping("/compra/pago") //url a la  q voy
	public String pagar(Model model){
		model.addAttribute("titulo", "Pagar"); //
		
		return "views/pagoView"; //html que traigo
	}
	
	@RequestMapping(value = "/compra/pago/form", method = RequestMethod.POST)
	public String GuardarPago(@Valid Pago pago, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Pago");
		    model.addAttribute("error", "Error al validar datos");
			return "views/pagoView";
		}
		try {
	
		} catch (Exception e) {
			model.addAttribute("error", "Error al realizar el pago: " + e.getMessage());
			return "views/pagoView";
		}
		return "views:/compraExitosaView"; //crear vista compra exitosa
	}
	
}
