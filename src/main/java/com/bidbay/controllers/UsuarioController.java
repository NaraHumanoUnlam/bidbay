package com.bidbay.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
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
			model.addAttribute("rol",session.getAttribute("rol"));
			model.addAttribute("titulo", "Perfil de " + usuario.getNick().toUpperCase());
			model.addAttribute("cantidadPublicaciones", productoService.productoDelUsuario(usuario.getId()).size());
			model.addAttribute("cantidadVentas", productoService.ventasDelUsuario(usuario.getId()));
			model.addAttribute("cantidadFavoritos",productoService.detalleFavoritosDelUsuario(usuario.getId()).size());
			model.addAttribute("listaFavoritos", productoService.detalleFavoritosDelUsuario(usuario.getId()));
			model.addAttribute("listaPublicaciones", productoService.productoDelUsuario(usuario.getId()));
			model.addAttribute("listaVentas", productoService.detalleVentasDelUsuario(usuario.getId()));
			model.addAttribute("cantidadCompras", productoService.comprasDelUsuario(usuario.getId()));
			model.addAttribute("listaCompras", productoService.detalleComprasDelUsuario(usuario.getId()));
			return "views/perfilUsuarioView";
		}
	}
	
	@RequestMapping(value="/agregar", method = RequestMethod.GET)
	public String crear(Map <String, Object> model) {
		model.put("titulo", "Registro Usuario");
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("botonSubmit", "Crear usuario");
		return "views/register";
	}
	
	@RequestMapping(value="/editar/{id}", method = RequestMethod.GET)
	public String editarUsuario(@PathVariable(value = "id") Long id,HttpSession session,Map <String, Object> model) {
		Usuario usuario;
		if (id>0) {
			usuario = usuarioService.findById(id);
			model.put("usuario", usuario);
		}
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));
		}
		
		model.put("titulo", "Editar Usuario");
		model.put("botonSubmit", "Editar");
		return "views/register";
	}
	
	@GetMapping("/logOut")
	public String logOutPerfil(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/home";
	}
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "views/usuariosView";
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarUsuario(@Valid Usuario usuario, BindingResult result,HttpSession session, Model model) {

	    if (usuario.getId() != null) {
	    	usuarioService.saveEdit(usuario);   
	    	 return "redirect:/usuario/perfil";
	    } else {
	    	 Usuario usuarioExistente = usuarioService.validarExistenciaUsuario(usuario.getNick(), usuario.getEmail());
	    	 if (usuarioExistente != null) {
	    		 model.addAttribute("titulo", "Registro Usuario");
		         model.addAttribute("error", "El usuario y/o email ya existe registrado.");
		         return "views/register";
	    	 }
	    	 usuarioService.save(usuario);
	    	 return "redirect:/login";
	    }
	}

}
