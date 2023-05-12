package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
class LoginController {
@Autowired
	private IUsuarioService usuarioService;

	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loguear(Model model) {
		return "views/login";
	}
	
	@RequestMapping(value="usuarios", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "views/usuariosView";
	}
	
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String validarLogin(@RequestParam("nick") String nick, @RequestParam("password") String password, Model model) {
	    Usuario usuarioBuscado = usuarioService.validarUsuario(nick, password);
	    if (usuarioBuscado == null) {
	        model.addAttribute("error", "Usuario y/o contraseña inválidos.");
	        return "views/login";
	    } else {
	    	 model.addAttribute("logueo", "Usuario validado OK.");
	        return "index";
	    }
	}

	
	@RequestMapping(value="usuario/agregar", method = RequestMethod.GET)
	public String crear(Map <String, Object> model) {
		model.put("titulo", "Registro Usuario");
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		return "views/register";
	}
	
	@RequestMapping(value = "usuario/agregar", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("titulo", "Registro Usuario");
	        return "views/login";
	    }

	    try {
	        usuarioService.save(usuario);
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al guardar el usuario: " + e.getMessage());
	        return "views/register";
	    }

	    return "redirect:/login";
	}
}