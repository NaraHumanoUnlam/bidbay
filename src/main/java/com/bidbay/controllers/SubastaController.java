package com.bidbay.controllers;

import java.io.IOException;
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
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.ICategoriaService;
import com.bidbay.service.IProductoService;
import com.bidbay.service.ISubastaService;
import com.bidbay.service.IUsuarioService;
import com.bidbay.utils.MyTimer;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@SessionAttributes("subasta")
@RequestMapping("/subasta")
@Controller
public class SubastaController {
	
	private Timer timer;
    private MyTimer timerTask;
    
	@Autowired
	private ISubastaService subastaServ;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listaSubastas(Model model) {
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
		
		String formatoFechaHTML = "yyyy-MM-dd";

	    String formatoFechaSQL = "yyyy-MM-dd";

	    try {
	      SimpleDateFormat sdfHTML = new SimpleDateFormat(formatoFechaHTML);

	      SimpleDateFormat sdfSQL = new SimpleDateFormat(formatoFechaSQL);

	      Date fecha = sdfHTML.parse(subasta.getFechaLimite().toString());

	      String fechaSQLString = sdfSQL.format(fecha);

	      java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaSQLString);
	      subastaServ.crearSubasta(subasta.getPrecioInicial(),fechaSQL,subasta.getHoraLimite() ,subasta.getSubastador());

	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
		
		
		return "redirect:/crear/producto";
	}
	
	@RequestMapping(value = "/crear/producto", method = RequestMethod.GET)
	public String formularioProdcuto(HttpSession session, Map<String, Object> model) throws Exception {
		if (usuarioService.chequearQueElUsuarioEsteLogeado(session) == false) {
			return "redirect:/login";
		} else {
			Usuario user = usuarioService.getUsuarioActualmenteLogeado(session);
			Producto producto = new Producto();
			producto.setCategoria(categoriaService.findOne(1L));
			producto.setVendedor(user);
			model.put("producto", producto);
			model.put("titulo", "¿Qué querés vender?");
			model.put("botonSubmit", "Vender");
			model.put("categorias", categoriaService.findAll());
			model.put("logueo",session.getAttribute("logueo"));
			model.put("rol",session.getAttribute("rol"));

			return "views/subastaProductoForm";
		}
	}
	
	@RequestMapping(value = "/crear/producto", method = RequestMethod.POST)
	public String guardarProdcuto(@Valid @ModelAttribute Producto producto, BindingResult result,@PathVariable(value = "id") Long id, Model model,
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
		subastaServ.agregarProducto(producto,id);
		return "redirect:/subasta/listar";
	}
	
	@RequestMapping(value = "/subasta/poducto/{id}")
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
	
	@RequestMapping(value ="/start-timer", method = RequestMethod.GET)
    public String startTimer(Model model){
		   
        return "views/TimerTest";
    }

    @GetMapping("/stop-timer")
    public String stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        return "timer-stopped";
    }
	

}
