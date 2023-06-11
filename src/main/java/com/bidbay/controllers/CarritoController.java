package com.bidbay.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICarritoService;
import com.bidbay.service.IProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
//@SessionAttributes("carrito")
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model, HttpSession session) {
    	if(!chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        model.addAttribute("titulo", "Listado de carrito");
        model.addAttribute("carrito", carritoService.findOneByUserID(idUsuario));
        model.addAttribute("precioTotal", carritoService.calcularPrecioTotal(idUsuario));
        return "views/carritoView";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String agregarProductoAlCarrito(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
    	//En realidad el método es una poronga aún, pero esta en el servicio!!
    	if(!chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
        carritoService.addProductToCarrito((Long) session.getAttribute("idUsuario"), id, redirectAttributes);
        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String editar(@PathVariable(value = "id") Long id, HttpSession session , Model model, @RequestParam("cantidadProductos") int stock, RedirectAttributes redirectAttributes) {
    	if(!chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
        carritoService.editCarritoItem((Long) session.getAttribute("idUsuario"), id, stock, redirectAttributes);
        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public String eliminarProductoDeCarrito(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
    	if(!chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
        carritoService.deleteCarritoItem((Long) session.getAttribute("idUsuario"), id, redirectAttributes);
        return "redirect:/carrito/listar";
    }
    
    private Boolean chequearQueElUsuarioEsteLogeado(HttpSession session) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return false;
        }
        return true;
    }
        

}