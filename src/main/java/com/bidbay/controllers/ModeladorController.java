package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bidbay.models.entity.RolUsuario;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IFavoritosService;
import com.bidbay.service.UsuarioServiceImpl;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/moderador")
public class ModeladorController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IFavoritosService favoritoService;
	

	@GetMapping("/perfilModerador")
	public String showPerfil(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			if (session.getAttribute("rol") != "Modelador") {
				return "redirect:/home";
			}
			Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("usuario", usuario);
			model.addAttribute("rol", "Modelador");
			model.addAttribute("titulo", "Perfil de " + usuario.getNick().toUpperCase());
			model.addAttribute("cantidadPublicaciones", productoService.findAll().size());
			model.addAttribute("cantidadCompras", productoService.cantidadComprasVentas());
			model.addAttribute("cantidadVentas", productoService.cantidadComprasVentas());
			model.addAttribute("cantidadFavoritos",favoritoService.findAll().size());
			model.addAttribute("listaFavoritos",favoritoService.findAll());
			model.addAttribute("listaPublicaciones",productoService.findAll());
			model.addAttribute("listaVentas", productoService.detalleVentasDelUsuario(usuario.getId()));
			model.addAttribute("listaCompras", productoService.detalleComprasDelUsuario(usuario.getId()));
			return "views/perfilUsuarioView";
		}
	}	

}