package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bidbay.models.entity.RolUsuario;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IComprasService;
import com.bidbay.service.IDetalleCompraService;
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
	
	@Autowired
	private IComprasService comprasService;
	
	private IDetalleCompraService  detalleServices;
	
	
	@GetMapping("/perfilModerador")
	public String showPerfil(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			if (!session.getAttribute("rol").equals("Modelador")) {
				return "redirect:/home";
			}
			System.out.println(session.getAttribute("rol"));
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
			model.addAttribute("listaVentas", productoService.findAllComprasVentas());
			model.addAttribute("listaCompras",productoService.findAllComprasVentas());
			return "views/perfilUsuarioView";
		}
	}	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
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
		}else {
			return "redirect:/login";
		} 
		model.addAttribute("titulo", "Listado de Detalles de Compra: " + id);
		model.addAttribute("detalles", detalleServices.listarDetallePorId(id));
		return "views/detalleMisComprasView";
	}

}