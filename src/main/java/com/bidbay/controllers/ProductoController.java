package com.bidbay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;
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
	private UsuarioServiceImpl usuarioService;
		
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam("name") @Nullable String name, @RequestParam("order") @Nullable String order,
			@RequestParam("search") @Nullable String search, Model model) {
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productoService.findAll());
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
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String crear(HttpSession session, Map <String, Object> model) throws Exception {
		if(usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			usuarioService.getUsuarioActualmenteLogeado(session);
			Producto producto = new Producto();
			producto.setCategoria(categoriaService.findOne(1L));
			model.put("producto", producto);
			model.put("titulo", "¿Qué querés vender?");
			model.put("botonSubmit", "Vender");
			model.put("categorias", categoriaService.findAll());
			return "views/productoForm";
		}
	}

	@PostMapping("/form")
	public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model,
			@RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes attibute) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Producto");
			return "views/productoForm";
		}

		if (!imagen.isEmpty()) {
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
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
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
	                     @RequestParam(value = "search", required = false) String search,
	                     Model model) {
	    model.addAttribute("titulo", "Búsqueda de Productos");
	    model.addAttribute("inputValue", search);
	    model.addAttribute("categorias", categoriaService.findAll());

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
	public String detalles(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Producto p = null;
		if (id > 0) {
			p = productoService.findOne(id);
		} else {
			return "views/productoDeatailView";
		}
		model.put("producto", p);
		model.put("titulo", "Detalles del Producto");
		model.put("categorias", categoriaService.findAll());
		return "views/productoDeatailView";
	}
	
	@PostMapping("/dejarReseña/{id}")
	public String dejarReseña(@PathVariable("id") Long idProducto, @RequestParam("mensaje") String mensaje, @RequestParam("puntaje") int puntaje, HttpSession session) {
		if (!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
			return "redirect:/login";
		}
		
		productoService.dejarReseña(idProducto, mensaje, puntaje, session);
		return "redirect:/producto/details/{idProducto}";
	}
	
	

	// esto es por mockito. ok tonces no lo borro xd
	public Producto someMethod() {
		// TODO Auto-generated method stub
		return new Producto();
	}


}
