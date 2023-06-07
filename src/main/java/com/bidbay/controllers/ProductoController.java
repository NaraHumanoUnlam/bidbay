package com.bidbay.controllers;

import java.util.Map;

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
import com.bidbay.models.entity.Producto;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;
import org.springframework.lang.Nullable;
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
	public String crear(Map<String, Object> model) {
		Producto producto = new Producto();
		producto.setCategoria(categoriaService.findOne(1L));
		model.put("producto", producto);
		model.put("titulo", "Formulario de Producto");
		model.put("botonSubmit", "Crear");
		return "views/productoForm";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid @ModelAttribute Producto producto,BindingResult result,
			Model model, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes attibute) {
	
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Producto");
			return "views/productoForm";
		}
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//imagenes");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				producto.setImagen(imagen.getOriginalFilename());
			
			}catch (IOException e) {
				e.printStackTrace();
			}
		} 		
		try {
			productoService.save(producto);
		} catch (Exception e) {
			model.addAttribute("error", "Error al guardar el producto: " + e.getMessage());
			System.out.println("mensaje de exepcion");
			return "views/productoForm";
		}
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
	public String buscar(@RequestParam("name") @Nullable String name, @RequestParam("order") @Nullable String order,
			@RequestParam("search") @Nullable String search, Model model) {
		model.addAttribute("titulo", "Busqueda de Productos");
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("inputValue", search);
		if (search != null && order == null) {
			List<Producto> productosEncontrados = new ArrayList<>();
			productosEncontrados.addAll(productoService.findByName(search.toString()));
			model.addAttribute("productos", productosEncontrados);
		} else if( order != null && search == null){
			model.addAttribute("productos", productoService.orderList(order));
		} if (search != null && order != null) {
			List<Producto> productosEncontrados = new ArrayList<>();
			productosEncontrados.addAll(productoService.findByName(search.toString()));
			model.addAttribute("productos", productoService.orderFiltredList(order, productosEncontrados));
		}
		return "views/productoSearhView";
	}
	
	@RequestMapping(value = "/publicacion/listar", method = RequestMethod.GET)
	public String listarPubliaciones(Model model) {
		//aca van a estar todas las publicaciones hechas igual que el index :P pero con diferente vista
		model.addAttribute("titulo", "Publicaciones de productos");
		model.addAttribute("productos", productoService.findAll());
		return "views/publicacionView";
	}
	
	@RequestMapping(value = "/publicacion/crear", method = RequestMethod.POST)
	public String crearPublicacion(@Valid Producto producto, BindingResult result, Model model) {
		//aca va a estar el mercado libre trucho :B
		productoService.save(producto);
		model.addAttribute("titulo", "Crear Publicacion");
		model.addAttribute("producto");
		return "views/crearPublicacionView";
	}

	//esto es por mockito. ok tonces no lo borro xd
	public Producto someMethod() {
		// TODO Auto-generated method stub
		return new Producto();
	}
	

}
