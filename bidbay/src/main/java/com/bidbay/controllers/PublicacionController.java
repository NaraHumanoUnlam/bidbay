package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.service.IPublicacionService;

@Controller
@SessionAttributes("publicacion")
@RequestMapping("/publicacion")
public class PublicacionController {
	
	@Autowired
	private IPublicacionService publicacionService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Publicaciones");
		model.addAttribute("publicaciones", publicacionService.findAll());
		return "views/publicacionView";
	}
}
