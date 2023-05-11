package com.bidbay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Bidbay");
		return "index";
	}
	
	
	
	
}
