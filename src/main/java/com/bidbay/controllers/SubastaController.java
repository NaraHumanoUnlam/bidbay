package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Subasta;
import com.bidbay.service.ISubastaService;

@SessionAttributes("subasta")
@Controller
public class SubastaController {
	@Autowired
	private ISubastaService subastaServ;
	
	@GetMapping("/{id}")
	public String listaProductos(@PathVariable Long id,Model model) {
		Subasta subastanueva =subastaServ.obtenerSubasta(id);
		model.addAttribute("subasta", subastanueva);
		return "subasta";
	}
}
