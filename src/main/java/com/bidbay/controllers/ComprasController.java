package com.bidbay.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IComprasService;
import com.bidbay.service.IDetalleCompraService;
import com.bidbay.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("miscompras")
@RequestMapping("/miscompras")
public class ComprasController {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private IComprasService comprasService;
	
	@Autowired
	private IDetalleCompraService detalleServices;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Compras");
		model.addAttribute("compras", comprasService.comprasDelUsuario(usuario.getId()));
		model.addAttribute("precioTotal", comprasService.calcularMontoTotalDeCompras(usuario.getId()));
		return "views/misComprasView";
	}
	
	@RequestMapping(value = "/detalle/{id}")
	public String vista(@PathVariable(value = "id") Long id,HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Detalles de Compra: " + id);
		model.addAttribute("detalles", detalleServices.listarDetallePorId(id));
		return "views/detalleMisComprasView";
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public String agregar(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		comprasService.crearCompra(usuarioService.getUsuarioActualmenteLogeado(session).getId());
		model.addAttribute("titulo", "Listado de Compras");
		model.addAttribute("compras", comprasService.comprasDelUsuario(usuario.getId()));
		model.addAttribute("precioTotal", comprasService.calcularMontoTotalDeCompras(usuario.getId()));
		return "redirect:/miscompras/listar";
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String agregarGet(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Compras");
		model.addAttribute("compras", comprasService.comprasDelUsuario(usuario.getId()));
		model.addAttribute("precioTotal", comprasService.calcularMontoTotalDeCompras(usuario.getId()));
		return "views/misComprasView";
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
	public String eliminarCompra(@PathVariable("id") Long id) {
	    comprasService.delete(id);
	    return "redirect:/miscompras/listar";
	}
}
