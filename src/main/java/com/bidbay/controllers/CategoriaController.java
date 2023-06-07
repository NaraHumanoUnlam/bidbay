package com.bidbay.controllers;


import com.bidbay.service.CategoriaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("categoria")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", categoriaService.findAll());
		return "views/categoriaView";
	}


   

}
