package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loguear(Model model) {
		return "views/login";
	}
	
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String validarLogin(@RequestParam("username") String nick, @RequestParam("password") String password, HttpSession session, Model model) {
	    Usuario usuarioBuscado = usuarioService.validarUsuario(nick, password);
	   
	    if (usuarioBuscado == null) {
	        model.addAttribute("error", "Usuario y/o contraseña inválidos.");
	        return "views/login";
	    } else {
	    	session.setAttribute("idUsuario", usuarioBuscado.getId());
	    	session.setAttribute("logueo", usuarioBuscado.getNick());
	    	switch (usuarioBuscado.getRol()) {
	    	case ROL_USUARIO:
	    		session.setAttribute("rol", "Usuario");
	    		break;
	    	case ROL_MODERADOR:
	    		session.setAttribute("rol", "Moderador");
	    		break;
	    	}
	    	 model.addAttribute("rol", session.getAttribute("rol"));
	        return "redirect:home";
	    }
	}
	
}