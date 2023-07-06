package com.bidbay.controllers;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IComprasService;
import com.bidbay.service.IPagoService;
import com.bidbay.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
@Controller
public class PagoController {
	
	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IComprasService compraService;

	
	@RequestMapping(value = "/pago/form/{idCompra}/{precioTotal}", method = RequestMethod.GET)
	public String pagarCompraParticular(@PathVariable("idCompra") Long id, @PathVariable("precioTotal") Double precioTotal, Model model) {
		//Double precio = precioTotal * 1.00;
		
		Pago pago = new Pago();
	
		model.addAttribute("pago", pago);
	    model.addAttribute("titulo", "Formulario de Pago particular");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    model.addAttribute("precioTotal", precioTotal);
	    model.addAttribute("pagoParticular", true);
		return "views/pagoView";
	}
	
	@RequestMapping(value = "/pago/form", method = RequestMethod.GET)
	public String pagarCompraTotal(@RequestParam("precioTotal") Double precioTotal, Model model) {
		Double precio = precioTotal;
		
		Pago pago = new Pago();
	
		model.addAttribute("pago", pago);
	    model.addAttribute("titulo", "Formulario de Pago total");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    model.addAttribute("precioTotal", precioTotal);
	    model.addAttribute("pagoParticular", false);
		return "views/pagoView";
	}
	
	@RequestMapping(value = "/pago/form", method = RequestMethod.POST)
	public String confirmoPagoTotal(@RequestParam("DNI") String DNI,
	                   @RequestParam("numeroTarjeta") String numeroTarjeta,
	                   @RequestParam("mes") String mes,
	                   @RequestParam("anio") String anio,
	                   @RequestParam("nombreDeCliente") String nombreDeCliente,
	                   @RequestParam("cvc") String cvc,
	                   @Valid Pago pago,
	                   BindingResult result,
	                   Model model, HttpSession session) {
	    Pago pagoNuevo = new Pago(DNI, Long.valueOf(numeroTarjeta), mes, anio, nombreDeCliente, Integer.valueOf(cvc));
	    Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
	    model.addAttribute("titulo", "Formulario de Pago");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    
	    Pago respuesta = pagoService.pagarTotal(pagoNuevo, usuario.getId());
	    if (respuesta.getAprobado()) {
	        model.addAttribute("ticket", respuesta);
	        java.util.Date fechaActual = new java.util.Date();
	        return "views/ticketValidadoView";
	    } else {
	        model.addAttribute("error", "El pago ha sido rechazado");
	        return "views/ticketRechazadoView";
	    }
	}
	
	@RequestMapping(value = "/pago/form/{idCompra}", method = RequestMethod.POST)
	public String confirmoPagoParticular(@RequestParam("DNI") String DNI,
	                   @RequestParam("numeroTarjeta") String numeroTarjeta,
	                   @RequestParam("mes") String mes,
	                   @RequestParam("anio") String anio,
	                   @RequestParam("nombreDeCliente") String nombreDeCliente,
	                   @RequestParam("cvc") String cvc,
	                   @Valid Pago pago,
	                   @PathVariable("idCompra") Long idCompra,
	                   BindingResult result,
	                   Model model) {
	    Pago pagoNuevo = new Pago(DNI, Long.valueOf(numeroTarjeta), mes, anio, nombreDeCliente, Integer.valueOf(cvc));
	    
	    Pago respuesta = pagoService.pagarParticular(pagoNuevo, idCompra);
	    
	    model.addAttribute("titulo", "Formulario de Pago");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    if (respuesta.getAprobado()) {
	        model.addAttribute("ticket", respuesta);
	        java.util.Date fechaActual = new java.util.Date();
	        return "views/ticketValidadoView";
	    } else {
	        model.addAttribute("error", "El pago ha sido rechazado");
	        return "views/ticketRechazadoView";
	    }
	}


}
