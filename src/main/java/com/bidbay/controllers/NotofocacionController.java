package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidbay.service.*;

@Controller
@RequestMapping("/notificacion")
public class NotofocacionController {
	@Autowired
	INotificacionService notificacionService;
	
	 @RequestMapping(value="/eliminar/{id}", method = RequestMethod.GET)
	 public String eliminar(@PathVariable(value = "id") Long id) {
		 notificacionService.eliminarNotificacion(id);
		 return "redirect:/";
	 }

}
