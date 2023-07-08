package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IReviewService;
import com.bidbay.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("review")
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private IReviewService reviewService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/dejarReview/{id}", method = RequestMethod.GET)
	public String mostrarFormularioReview(@PathVariable("id") Long idProducto, HttpSession session, Model model) {
		if (!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		Producto producto = productoService.findOne(idProducto);
		model.addAttribute("producto", producto);
		
		return "views/dejarReviewView";
	}
	
	@RequestMapping(value = "/dejarReview/{id}", method = RequestMethod.POST)
	public String guardarReview(@PathVariable("id") Long idProducto, @RequestParam("mensaje") String mensaje, @RequestParam("puntaje") int puntaje, HttpSession session) {
		if (!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
	    double puntajeDouble = (double) puntaje;
		reviewService.dejarReview(idProducto, mensaje, puntajeDouble, usuario.getId());
		return "redirect:/producto/details/" + idProducto;
	}

}