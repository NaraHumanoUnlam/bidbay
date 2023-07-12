package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.excepciones.ArchivoException;
import com.bidbay.models.dao.IModalidadDao;
import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Favoritos;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Review;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;
import com.bidbay.service.IReviewService;
import com.bidbay.service.ModalidadServiceImpl;
import com.bidbay.service.UsuarioServiceImpl;

import org.springframework.lang.Nullable;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@SessionAttributes("producto")
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IReviewService reviewService;

	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private ModalidadServiceImpl modalidadService;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam("name") @Nullable String name, @RequestParam("order") @Nullable String order,

			HttpSession session,@RequestParam("search") @Nullable String search, Model model) {
		
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
			model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
		}else {
			return "redirect:/login";
		}
		Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productoService.productoDelUsuario(user.getId()));
		if (search != null) {
			List<Producto> productosEncontrados = new ArrayList<>();
			productosEncontrados.addAll(productoService.findByNameDelUsuario(search.toString(), user.getId()));
			model.addAttribute("productos", productosEncontrados);
			model.addAttribute("inputValue", search);
		} else {
			if (order != null) {
				model.addAttribute("productos", productoService.orderListDelUsuario(order, user.getId()));
			}
		}
		return "views/productoView";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(HttpSession session, Map<String, Object> model) throws Exception {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));
			model.put("idUsuario",session.getAttribute("idUsuario"));
		}else {
			return "redirect:/login";
		}
			Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
			Producto producto = new Producto();
			producto.setCategoria(categoriaService.findOne(1L));
			producto.setModalidad(modalidadService.findOne(1L));
			producto.setVendedor(user);
			model.put("producto", producto);
			model.put("titulo", "¿Qué querés vender?");
			model.put("botonSubmit", "Vender");
			model.put("categorias", categoriaService.findAll());
			model.put("modalidades", modalidadService.findAll());
			return "views/productoForm";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model,
			@RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes attibute) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Producto");
			return "views/productoForm";
		}

		if (imagen.isEmpty()) {
	        producto.setImagen("imagen-placeholder.jpg");
	    } else {
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
		return "redirect:/producto/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, HttpSession session) {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));
			model.put("idUsuario",session.getAttribute("idUsuario"));
		}else {
			return "redirect:/login";
		}
		Producto p = null;
		if (id > 0) {
			p = productoService.findOne(id);
		} else {
			return "views/productoForm";
		}
		model.put("producto", p);
		model.put("titulo", "Editar Producto");
		model.put("botonSubmit", "Editar");
		model.put("categorias", categoriaService.findAll());
		model.put("modalidades", modalidadService.findAll());
		return "views/productoForm";
	}

	@RequestMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			productoService.delete(id);
		}
		return "redirect:/producto/listar";
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String buscar(@RequestParam(value = "categoria", required = false) Long id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Model model, HttpSession session) {
		model.addAttribute("titulo", "Búsqueda de Productos");
		model.addAttribute("inputValue", search);
		model.addAttribute("categorias", categoriaService.findAll());
		model.addAttribute("modalidades", modalidadService.findAll());
		
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.addAttribute("logueo",session.getAttribute("logueo"));
			model.addAttribute("rol",session.getAttribute("rol"));
			model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
		}

		List<Producto> productos;

		if (id != null) {
			Categoria categoria = categoriaService.findOne(id);
			if (categoria != null) {
				productos = productoService.findByCategoriaId(categoria.getId());
				model.addAttribute("categoria", categoria);
			} else {
				productos = productoService.findAll();
			}
		} else {
			productos = productoService.findAll();
		}

		if (search != null && !search.isEmpty()) {
			productos = productoService.findByName(search);
		}

		if (order != null) {
			if (order.equals("asc")) {
				productos.sort(Comparator.comparing(Producto::getPrecio));
			} else if (order.equals("desc")) {
				productos.sort(Comparator.comparing(Producto::getPrecio).reversed());
			}
		}

		model.addAttribute("productos", productos);
		return "views/productoSearhView";
	}

	@RequestMapping(value = "/details/{id}")
	public String detalles(@PathVariable("id") Long id, @RequestParam(value = "fav", defaultValue = "false") boolean fav, Map<String, Object> model,
			HttpSession session) {
		Producto p = null;
		
		if (id > 0) {
			p = productoService.findOne(id);
		} else {
			return "views/productoDeatailView";
		}
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));
			model.put("idUsuario",session.getAttribute("idUsuario"));
			Usuario usuarioBuscado= usuarioService.getUsuarioActualmenteLogeado(session);
			Favoritos favorito = productoService.buscoFavoritoDelUsuario(usuarioBuscado.getId() ,p.getId());
			if (favorito != null) {
				model.put("valheard",true);
			} else {
				model.put("valheard",false);
			}
			
			if (fav) { 
				productoService.clickFavoritoDelUsuario(usuarioBuscado.getId(),p.getId());
				if (favorito != null) {
					model.put("valheard",false);
				} else {
					model.put("valheard",true);
				}
			}
			model.put("usuarioComproProducto", reviewService.usuarioHabilitado(usuarioBuscado.getId(), p.getId()));
		}
		model.put("producto", p);
		model.put("titulo", "Detalles del Producto");
	    model.put("reviews", reviewService.getReviewsPorProducto(id));    
		model.put("categorias", categoriaService.findAll());
		model.put("modalidades", modalidadService.findAll());
		
		return "views/productoDeatailView";
		
	}

	// esto es por mockito. ok tonces no lo borro xd
	public Producto someMethod() {
		// TODO Auto-generated method stub
		return new Producto();
	}

}
