package com.bidbay.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.excepciones.ArchivoException;
import com.bidbay.models.dao.IReviewDao;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IProductoService;
import com.bidbay.service.TicketServicesImpl;
import com.bidbay.service.IComprasService;
import com.bidbay.service.IDetalleCompraService;
import com.bidbay.service.IFavoritosService;
import com.bidbay.service.UsuarioServiceImpl;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/moderador")
public class ModeradorController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IFavoritosService favoritoService;
	
	@Autowired
	private IComprasService comprasService;
	
	@Autowired
	private IDetalleCompraService  detalleServices;
	
	@Autowired
	private TicketServicesImpl servicioTicket;
	
	@Autowired
	private IReviewDao reviewDao;
	
	@GetMapping("/perfilModerador")
	public String showPerfil(HttpSession session, Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("usuario", usuario);
			model.addAttribute("rol",session.getAttribute("rol"));
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
	
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	public String compras(HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Compras");
		model.addAttribute("compras", comprasService.findAll());
		model.addAttribute("precioTotal", 0);
		return "views/misComprasView";
	}
	
	@RequestMapping(value = "/detalle/{id}")
	public String detalleCompras(@PathVariable(value = "id") Long id,HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		model.addAttribute("titulo", "Listado de Detalles de Compra: " + id);
		model.addAttribute("detalles", detalleServices.listarDetallePorId(id));
		return "views/detalleMisComprasView";
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public String listar(HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Pagos");
		model.addAttribute("tickets", servicioTicket.findAll());
		//model.addAttribute("precioTotal", pagoDao.calcularMontoTotalDePagos(usuario.getId()));
		return "views/misPagosView";
	}
	
	@RequestMapping(value = "/ticketdet/{id}")
	public String detalleProductoTicket(@PathVariable(value = "id") Long id,HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		model.addAttribute("titulo", "Listado Productos del Ticket: " + id);
		model.addAttribute("detalles", servicioTicket.detallesProductosPorTicket(id));
		return "views/detalleMisComprasView";
	}

	
	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public String listar(@RequestParam("name") @Nullable String name, @RequestParam("order") @Nullable String order,
			HttpSession session,@RequestParam("search") @Nullable String search, Model model) {
		
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos",productoService.findAll());
		if (search != null) {
			List<Producto> productosEncontrados = new ArrayList<>();
			productosEncontrados.addAll(productoService.findByName(search.toString()));
			model.addAttribute("productos", productosEncontrados);
			model.addAttribute("inputValue", search);
		} else {
			if (order != null) {
				model.addAttribute("productos", productoService.orderList(order));
			}
		}
		return "views/productoView";
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public String review(HttpSession session,Model model) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			if (!session.getAttribute("rol").equals("Moderador")) {
				return "redirect:/home";
			}
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
		}else {
			return "redirect:/login";
		} 
		
		Usuario usuario = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Reseñas");
		model.addAttribute("reviews", reviewDao.findAll());
		return "views/reviewView";
	}
	
	@RequestMapping(value = "/reviewDel/{id}")
	public String eliminarReview(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			reviewDao.deleteById(id);
		}
		return "redirect:/moderador/review";
	}
	
	@PostMapping("/producto/guardar")
	public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model,
			@RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes attibute) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Producto");
			return "views/productoForm";
		}

		if (!imagen.isEmpty()){
			Path directorioImagenes = Paths.get("src//main//resources//static//imagenes");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				producto.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {
				throw new ArchivoException("Error al escribir archivo", e);
			}
		}
		productoService.save(producto);
		return "redirect:/moderador/productos";
	}
	
	@RequestMapping(value = "producto/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			productoService.deleteFromView(id);
		}
		return "redirect:/moderador/productos";
	}

}