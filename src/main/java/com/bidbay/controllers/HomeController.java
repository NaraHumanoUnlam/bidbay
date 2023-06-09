package com.bidbay.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bidbay.models.entity.Notificacion;
import com.bidbay.service.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private INotificacionService servNoti;
	
	@GetMapping(value = { "/", "/home","/index" })
	public String listar(Model model, HttpSession session) {
		model.addAttribute("titulo", "Bidbay");
		//filtra por modalidad 'Venta' por favor mantener con el id de modalidad venta
		model.addAttribute("productos", productoService.obtenerPorModalidad(1l));
		
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
			model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
			Long userID = usuarioService.getUsuarioActualmenteLogeado(session).getId();
			notificaciones = servNoti.findAllByUser(userID);
		}
		model.addAttribute("notificaciones", notificaciones == null ? "Sin notificaciones": notificaciones );
		return "index";
	}
	
	
	@PostMapping("/")
	public String listarNotificaciones(Model model, HttpSession session) {
		
		if(session.getAttribute("idUsuario")!=null) {
			Long idUsuario = (Long) session.getAttribute("idUsuario");
			return "views/Notificacion";
		}
		model.addAttribute("titulo", "Bidbay");
		model.addAttribute("productos", productoService.findAll());
		return "index";
		
	}
}
