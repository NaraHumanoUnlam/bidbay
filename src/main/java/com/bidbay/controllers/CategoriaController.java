package com.bidbay.controllers;

import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.CategoriaServiceImpl;
import com.bidbay.service.ProductoServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("categoria")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImpl categoriaService;
    
    @Autowired
    private ProductoServiceImpl productoService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Categorias");
		model.addAttribute("categorias", categoriaService.findAll());
		return "views/categoriaView";
	}

	@RequestMapping(value = "/productos/{id}", method = RequestMethod.GET)
	public String mostrarProductosPorCategoria(@PathVariable("id") Long id, Model model) {
	    Categoria categoria = categoriaService.findOne(id);
	    if (categoria == null) {
	        return "redirect:/categoria/listar";
	    }
	    List<Producto> productos = productoService.findByCategoriaId(categoria.getId());
	    model.addAttribute("categoria", categoria);
	    model.addAttribute("productos", productos);
	    return "views/productosPorCategoriaView";
	}


   

}
