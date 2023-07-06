package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private IProductoService productoService;

	@GetMapping("/perfil")
	public String showPerfil(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("usuario", usuario);
			model.addAttribute("titulo", "Perfil de: " + usuario.getNick());
			return "views/perfilUsuarioView";
		}


	}
	
	@GetMapping("/logOut")
	public String logOutPerfil(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/home";
	}
	
	
	

}
