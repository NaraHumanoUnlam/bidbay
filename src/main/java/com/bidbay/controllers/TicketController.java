package com.bidbay.controllers;

import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.models.dao.IPagoDao;
import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Ticket;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IUsuarioService;
import com.bidbay.service.TicketServicesImpl;

@Controller
public class TicketController {

	@Autowired
	private TicketServicesImpl servicioTicket;
	@Autowired
	private IPagoDao pagoDao; 
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@RequestMapping(value = "/ticketValidado/{ticketId}", method = RequestMethod.GET)
	public String pagoAprobado(@PathVariable("ticketId")Long ticketCreado, Map<String, Object> model) {
	    try {

	    	servicioTicket.findOne(ticketCreado);
	    	
	        model.put("ticket", ticketCreado);

	      
	        model.put("title", "Pago Aprobado");
	        model.put("index", "Regresar al Inicio");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "views/ticketValidadoview";
	}
	
}	


//@RequestMapping(value = "/ticketValidado", method = RequestMethod.GET)
//public String pagoAprobado(@RequestParam("idPago") Long idPago, Map<String, Object> model) {
//    try {
//        Optional<Pago> pago;
//        Ticket ticket = new Ticket();
//        ticket.setIdTicket(125L); // Establece el valor de idTicket correctamente
//        model.put("ticket", ticket);
//        pago = pagoDao.findById(idPago);
//        model.put("title", "Pago Aprobado");
//        model.put("index", "Regresar al Inicio");
//        model.put("pago", pago);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return "views/ticketValidadoview";
//}