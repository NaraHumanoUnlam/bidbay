package com.bidbay.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.excepciones.ArchivoException;
import com.bidbay.models.entity.Ofertante;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;
import com.bidbay.service.ISubastaService;
import com.bidbay.service.IUsuarioService;
import com.bidbay.service.ModalidadServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@SessionAttributes("subasta")
@RequestMapping("/subasta")
@Controller
public class SubastaController {
    
	@Autowired
	private ISubastaService subastaServ;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ModalidadServiceImpl modalidadService;


	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaSubastas(HttpSession session, Model model) {
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
		
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
		List<Subasta> subastas = subastaServ.findAll();
		model.addAttribute("subastas", subastas);
		return "views/subastaView";
	}
	
	@RequestMapping(value = "/mostrar/{id}", method = RequestMethod.GET)
	public String mostrarSubasta(@PathVariable(value = "id") Long id,Model model) {
		Subasta subasta = subastaServ.obtenerSubasta(id);
		model.addAttribute("subasta", subasta);
		return "views/subastaView";
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminarSubasta(@PathVariable(value = "id") Long id,Model model) {
		subastaServ.eliminarUna(id);
		model.addAttribute("mensaje", "Se elimino la subasta");
		return "views/subastaView";
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String crearSubasta(HttpSession session, Map<String, Object> model) throws Exception {
		if (usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
			Producto producto = new Producto();
			Subasta subasta = new Subasta();
			producto.setCategoria(categoriaService.findOne(1L));
			producto.setVendedor(user);
			subasta.setSubastador(user);
			subastaServ.save(subasta);
			model.put("subasta", subasta);
			model.put("titulo", "Formulario de Subasta");
			model.put("botonSubmit", "Continuar");
			model.put("categorias", categoriaService.findAll());
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));

			return "views/subastaFormView";
		}
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute Subasta subasta, BindingResult result, Model model,RedirectAttributes attibute) {

	    try {

	      subasta.setMaximo(subasta.getPrecioInicial());
	      subasta.setSubastador(subasta.getSubastador());
	      System.out.println("id: " + subasta.getId() + " precio inicial: " + subasta.getPrecioInicial() + " fecha limite: " + subasta.getFechaLimite()+ " hora: " + subasta.getHoraLimite()+ " id usuario = " + subasta.getSubastador().getId());
	     subastaServ.save(subasta);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	    model.addAttribute("idsubasta", subasta.getId());
		
		return "redirect:/subasta/crear/producto/"+subasta.getId();
	}
	
	@RequestMapping(value = "/crear/producto/{idSubasta}", method = RequestMethod.GET)
	public String formularioProdcuto(HttpSession session, @PathVariable(value = "idSubasta") Long id,Map<String, Object> model) throws Exception {
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
			producto.setModalidad(modalidadService.findOne(2L));
			producto.setVendedor(user);
			model.put("producto", producto);
			model.put("titulo", "¿Qué querés subastar?");
			model.put("botonSubmit", "Subastar");
			model.put("categorias", categoriaService.findAll());
			model.put("modalidades", modalidadService.findAll());
			return "views/subastaProductoForm";

		}
	
	@RequestMapping(value = "/crear/producto/{idSubasta}", method = RequestMethod.POST)
	public String guardarProdcuto(@Valid @ModelAttribute Producto producto, BindingResult result,@PathVariable(value = "idSubasta") Long idSubasta, HttpSession session, Model model,
			@RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes attibute) {
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
		Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
		producto.setPrecio(subastaServ.obtenerSubasta(idSubasta).getPrecioInicial());
		producto.setStock(1);
		producto.setVendedor(user);
		productoService.save(producto);
		Subasta miSubastaGuardada = subastaServ.findById(idSubasta);
		miSubastaGuardada.setProducto(producto);
		subastaServ.save(miSubastaGuardada);
		return "redirect:/subasta/listar";
	}
	
	@RequestMapping(value = "/editar/poducto/{id}")
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
	
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String mostrar(HttpSession session,@PathVariable(value = "id") Long idSubasta, Model model) {
		if(!usuarioService.chequearQueElUsuarioEsteLogeado(session)) {
    		return "redirect:/login";
    	}
		
		model.addAttribute("logueo",session.getAttribute("logueo"));
		model.addAttribute("rol",session.getAttribute("rol"));
		model.addAttribute("idUsuario",session.getAttribute("idUsuario"));
		Subasta miSubasta = subastaServ.obtenerSubasta(idSubasta);
		Producto product = miSubasta.getProducto();
		Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
		Ofertante ofertante = new Ofertante();
		ofertante.setUsuario(user);
		System.out.println(ofertante.getUsuario().getId() + " " + miSubasta.getSubastador().getId() );
		model.addAttribute("ofertante", ofertante );
		model.addAttribute("producto",product);
		model.addAttribute("subasta",miSubasta);
		return "views/subastaDetailView";
	}
	
	@RequestMapping(value = "/ofertar/{id}", method = RequestMethod.GET)
	public String ofertarView(HttpSession session,@PathVariable(value = "id") Long idSubasta, Model model) {
		Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
		Subasta miSubasta = subastaServ.obtenerSubasta(idSubasta);
		Ofertante ofertante = new Ofertante();
		ofertante.setUsuario(user);
		model.addAttribute("ofertante",ofertante);
		model.addAttribute("subasta",miSubasta);
		model.addAttribute("botonSubmit", "Ofertar");
		return "views/ofertarView";
	}
	
	@RequestMapping(value = "/ofertar/{id}", method = RequestMethod.POST)
	public String ofertar(@PathVariable(value = "id") Long idSubasta,@ModelAttribute Ofertante ofertante,BindingResult result, Model model) {
		Subasta miSubasta = subastaServ.obtenerSubasta(idSubasta);
		if(ofertante.getOferta() > miSubasta.getMaximo()) {
			miSubasta.setMaximo(ofertante.getOferta());
		}
		subastaServ.save(miSubasta);
		subastaServ.agregarOfertante(ofertante,idSubasta);
		model.addAttribute("botonSubmit", "Ofertar");
		model.addAttribute("subasta",miSubasta);
		return "redirect:/subasta/details/"+miSubasta.getId();
	}
	
	@RequestMapping(value = "/comprar/{id}", method = RequestMethod.POST)
	public String asignarProductoACompra(@PathVariable(value = "id") Long idSubasta,@ModelAttribute Ofertante ofertante,BindingResult result, Model model) {
		Subasta miSubasta = subastaServ.obtenerSubasta(idSubasta);
		if(ofertante.getOferta() > miSubasta.getMaximo()) {
			miSubasta.setMaximo(ofertante.getOferta());
		}
		subastaServ.save(miSubasta);
		subastaServ.agregarOfertante(ofertante,idSubasta);
		model.addAttribute("botonSubmit", "Ofertar");
		model.addAttribute("subasta",miSubasta);
		return "redirect:/subasta/details/"+miSubasta.getId();
	}
	

}
