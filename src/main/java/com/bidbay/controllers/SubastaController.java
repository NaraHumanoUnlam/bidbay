package com.bidbay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Subasta;
import com.bidbay.service.ISubastaService;
import com.bidbay.service.IUsuarioService;

@SessionAttributes("subasta")
@RequestMapping("/subasta")
@Controller
public class SubastaController {
	@Autowired
	private ISubastaService subastaServ;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaSubastas(Model model) {
		List<Subasta> subastas = subastaServ.findAll();
		model.addAttribute("subastas", subastas);
		return "views/subastaView";
	}
	

}
