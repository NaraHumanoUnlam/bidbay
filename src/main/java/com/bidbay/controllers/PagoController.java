package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IPagoService;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagoController {
	@Autowired
	private ICarritoService carritoService;
	@Autowired
	private IPagoService pagoService; 
	
//	@GetMapping("/pago")
//	public String comprar(Model model) {
//		model.addAttribute("titulo", "Compra");
//		model.addAttribute("carrito", carritoService.findAll());
//		return "views/compraView";
//	}
	
//

	@RequestMapping(value = "/pago/form", method = RequestMethod.GET)
	public String crear(@RequestParam("precioTotal") Double precioTotal, Map<String, Object> model) {
		Double precio = precioTotal;
		Pago pago = new Pago();
//		Carrito carraso = new Carrito(); 
		model.put("precioTotal", precio);
//		model.put("carrito", carraso);
		model.put("pago", pago);
		model.put("titulo", "Formulario de Pago");
		model.put("botonSubmit", "Realizar Pago");
		return "views/pagoView";
	}
	
	
		

	
	@RequestMapping(value = "/pago/form", method = RequestMethod.POST)
	public String pago(@Valid Pago pago, BindingResult result, Model model)  {
			model.addAttribute("titulo", "Formulario de Pago");
			model.addAttribute("botonSubmit", "Realizar Pago");
			var respuesta=pagoService.pagar(pago);
			if(respuesta.getAprobado()){
				model.addAttribute("idPago", respuesta.getIdPago());
//		        model.addAttribute("usuario", respuesta.getUsuario());
//		        model.addAttribute("producto", respuesta.getProducto());
		        return "views/ticketValidadoview";
			}else {
				  model.addAttribute("error", "El pago ha sido rechazado");
			      return "views/ticketRechazadoView";
			}

	}
	
//	@RequestMapping(value = "/pago/form", method = RequestMethod.POST)
//	public String pago(@Valid Pago pago, BindingResult result, Model model) {
//	    model.addAttribute("titulo", "Formulario de Pago");
//	    model.addAttribute("botonSubmit", "Realizar Pago");
//	    var respuesta = pagoService.pagar(pago);
//	    if (respuesta.getAprobado()) {
//	        model.addAttribute("idPago", respuesta.getIdPago());
//	        model.addAttribute("successMessage", "¡Pago aceptado! Su ticket ha sido validado.");
//
//	        return "views/ticketValidadoview";
//	    } else {
//	        model.addAttribute("error", "El pago ha sido rechazado");
//	        return "views/ticketRechazadoView";
//	    }
//	}
//	
	
}
