package com.bidbay.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;
import com.bidbay.models.entity.Notificacion;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.INotificacionService;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;
    
    @Autowired
	private IUsuarioService usuarioService;
    
    @Autowired
    private INotificacionService notifiacionService;
    
   

    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model, HttpSession session) {
    	List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
    	Long idUsuario = usuarioService.getUsuarioActualmenteLogeado(session).getId();
    	notificaciones = notifiacionService.findAllByUser(idUsuario);
    	model.addAttribute("notificaciones", notificaciones == null ? "Sin notificaciones": notificaciones);
    	
        model.addAttribute("titulo", "Listado de carrito");
        model.addAttribute("carrito", carritoService.findOneByUserID(idUsuario));
        model.addAttribute("precioTotal", carritoService.calcularPrecioTotal(idUsuario));
        return "views/carritoView";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String agregarProductoAlCarrito(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
    	List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
    	Long idUsuario = usuarioService.getUsuarioActualmenteLogeado(session).getId();
    	notificaciones = notifiacionService.findAllByUser(idUsuario);
    	model.addAttribute("notificaciones", notificaciones == null ? "Sin notificaciones": notificaciones);
    	
        carritoService.addProductToCarrito((Long) session.getAttribute("idUsuario"), id, redirectAttributes);
        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String editar(@PathVariable(value = "id") Long id, HttpSession session , Model model, @RequestParam("cantidadProductos") int stock, RedirectAttributes redirectAttributes) {
    	List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
    	Long idUsuario = usuarioService.getUsuarioActualmenteLogeado(session).getId();
    	notificaciones = notifiacionService.findAllByUser(idUsuario);
    	model.addAttribute("notificaciones", notificaciones == null ? "Sin notificaciones": notificaciones);
    	
        carritoService.editCarritoItem((Long) session.getAttribute("idUsuario"), id, stock, redirectAttributes);
        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public String eliminarProductoDeCarrito(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
    	List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
    	Long idUsuario = usuarioService.getUsuarioActualmenteLogeado(session).getId();
    	notificaciones = notifiacionService.findAllByUser(idUsuario);
    	model.addAttribute("notificaciones", notificaciones == null ? "Sin notificaciones": notificaciones);
    	
        carritoService.deleteCarritoItem((Long) session.getAttribute("idUsuario"), id, redirectAttributes);
        return "redirect:/carrito/listar";
    }
    
        

}
