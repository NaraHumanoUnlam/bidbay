package com.bidbay.controllers;

import java.util.Map;

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
import com.bidbay.service.IPagoService;

import jakarta.validation.Valid;

@Controller
public class CompraController {
	@Autowired
	private ICarritoService carritoService;
	private IPagoService pagoService; 
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
	
	@RequestMapping(value = "/compra/pago/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {

		Pago pago = new Pago();
		model.put("pago", pago);
		model.put("titulo", "Formulario de Pago");
		model.put("botonSubmit", "Realizar Pago");
		return "views/pagoView";
	}
	
	@RequestMapping(value = "/compra/pago/form", method = RequestMethod.POST)
	public String GuardarPago(@Valid Pago pago, BindingResult result, Model model) {
			model.addAttribute("titulo", "Formulario de Pago");
			model.addAttribute("botonSubmit", "Realizar Pago");
			pagoService.pagarCompra(pago);
			return "views/pagoExitosoTemporal";
	}
	
	
	
}