package com.bidbay.controllers;

import java.sql.Date;
import java.time.LocalDate;
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

import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.Compras;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IComprasService;
import com.bidbay.service.INotificacionService;
import com.bidbay.service.IPagoService;
import com.bidbay.service.IUsuarioService;
import com.bidbay.service.NotificacionService;

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
	
	@Autowired
	private INotificacionService notificacionService;

	

	
	@RequestMapping(value = "/pago/form", method = RequestMethod.GET)
	public String pagarCompraTotal(@RequestParam("precioTotal") Double precioTotal, Model model) {
		Double precio = precioTotal;
		
		Pago pago = new Pago();
	
		model.addAttribute("pago", pago);
	    model.addAttribute("titulo", "Formulario de Pago Total");
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
	    Pago pagoNuevo = new Pago(DNI, numeroTarjeta, mes, anio, nombreDeCliente, cvc);
	    Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
	    model.addAttribute("titulo", "Formulario de Pago");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    
	    Pago respuesta = pagoService.pagarTotal(pagoNuevo, usuario.getId());
	    
	    if (respuesta.getAprobado()) {
	        model.addAttribute("ticket", respuesta);
	        return "views/ticketValidadoView";
	    } else {
	        model.addAttribute("error", "El pago ha sido rechazado");
	        //notificacionService.crearNotificacion("Pago denegado","Tu pago ha sido denegado!", usuario);
	        return "views/ticketRechazadoView";
	    }
	}
	
	
	@RequestMapping(value = "/pago/form/{idCompra}/{precioTotal}", method = RequestMethod.GET)
	public String pagarCompraParticular(@PathVariable("idCompra") Long id, @PathVariable("precioTotal") Double precioTotal, Model model) {
		
		Pago pago = new Pago();
		Compras compra = compraService.findOne(id);
	
		model.addAttribute("pago", pago);
	    model.addAttribute("titulo", "Formulario de Pago Particular");
	    model.addAttribute("botonSubmit", "Realizar Pago");
	    model.addAttribute("precioTotal", precioTotal);
	    model.addAttribute("pagoParticular", true);
	    model.addAttribute("compra", compra);
		return "views/pagoView";
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
	                   Model model, HttpSession session) {
		 Pago pagoNuevo = new Pago(DNI, numeroTarjeta, mes, anio, nombreDeCliente, cvc);
		 Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
	    Pago respuesta = pagoService.pagarParticular(pagoNuevo, idCompra, usuario.getId() );
	    
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
