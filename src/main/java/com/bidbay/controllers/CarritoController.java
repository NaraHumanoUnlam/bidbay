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

    @Autowired
    private IProductoService productoService;

    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model, HttpSession session) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            // Si el idUsuario es nulo, que vaya al login
            return "redirect:/login";
        }
        model.addAttribute("titulo", "Listado de carrito");
        model.addAttribute("carrito", carritoService.findOneByUserID(idUsuario));
        model.addAttribute("precioTotal", calcularPrecioTotal());
        return "views/carritoView";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public String agregar(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        //Este método es una poronga pero después se va a pasar a otro lado, no importa mientras funque
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            // Si el idUsuario es nulo, que vaya al login
            return "redirect:/login";
        }
    	Producto producto = productoService.findOne(id);
        List<Carrito> carritosActuales = carritoService.findAll();
        
        for (Carrito carrito : carritosActuales) {
            if (carrito.getIdUsuario().equals(idUsuario)) {
                List<CarritoItem> carritoItems = carrito.getCarritoItems();
                if(!carritoItems.isEmpty()) {
                	for (CarritoItem carritoItem : carritoItems) {
                        if (carritoItem.getProducto().equals(producto)) {
                            Integer stock = carritoItem.getStock();
                            stock++;
                            cambiarStockDelCarritoItem(carritoItem, carrito, stock, redirectAttributes);
                            return "redirect:/carrito/listar";
                        }
                    }
                }
                CarritoItem carritoItem = new CarritoItem(producto, 1);
                carrito.addCarritoItem(carritoItem);
                carritoService.save(carrito);
                redirectAttributes.addFlashAttribute("mensaje", "Producto agregado correctamente al carrito");
                return "redirect:/carrito/listar";
            }
        }
        // Si no se encuentra el carrito del usuario, se crea uno nuevo
        Carrito carrito = new Carrito(idUsuario);
        CarritoItem carritoItem = new CarritoItem(producto, 1);
        carrito.addCarritoItem(carritoItem);
        carritoService.save(carrito);
        redirectAttributes.addFlashAttribute("mensaje", "Producto agregado correctamente al carrito");
        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String editar(@PathVariable(value = "id") Long id, HttpSession session , Model model, @RequestParam("cantidadProductos") int stock, RedirectAttributes redirectAttributes) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            // Si el idUsuario es nulo, que vaya al login
            return "redirect:/login";
        }
    	Carrito carrito = carritoService.findOneByUserID(idUsuario);
    	CarritoItem carritoItem = carritoService.findCarritoItemById(id, carrito.getId());
        if (carritoItem == null) {
            return "redirect:/carrito/listar";
        }

        if (stock == 0) {
            redirectAttributes.addFlashAttribute("mensajeError", "El stock debe ser mayor a 0");
            return "redirect:/carrito/listar";
        }

        cambiarStockDelCarritoItem(carritoItem, carrito, stock, redirectAttributes);

        return "redirect:/carrito/listar";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public String eliminar(@PathVariable(value = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            // Si el idUsuario es nulo, que vaya al login
            return "redirect:/login";
        }
    	Carrito carrito = carritoService.findOneByUserID(idUsuario);
    	CarritoItem carritoItem = carritoService.findCarritoItemById(id, carrito.getId());

        if (carritoItem == null) {
            return "redirect:/carrito/listar";
        }

        carritoService.deleteCarritoItem(carritoItem, carrito.getId());
        redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado correctamente del carrito");

        return "redirect:/carrito/listar";
    }

    private void cambiarStockDelCarritoItem(CarritoItem carritoItem, Carrito carrito, Integer stockNuevo, RedirectAttributes redirectAttributes) {
        Producto producto = carritoItem.getProducto();
        if (producto.getStock() < stockNuevo) {
            stockNuevo = producto.getStock();
            redirectAttributes.addFlashAttribute("mensajeError", "Stock insuficiente, máximo " + stockNuevo.toString());
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito", "Stock actualizado correctamente");
        }
        carritoItem.setStock(stockNuevo);
        carritoService.saveCarritoItem(carritoItem, carrito.getId());
    }

    private Double calcularPrecioTotal() {
        List<Carrito> carritos = carritoService.findAll();

        Double precioTotal = 0.0;

        for (Carrito carrito : carritos) {
            List<CarritoItem> carritoItems = carrito.getCarritoItems();
            for (CarritoItem carritoItem : carritoItems) {
                Producto producto = carritoItem.getProducto();
                Integer stock = carritoItem.getStock();
                Double precio = producto.getPrecio();

                precioTotal += precio * stock;
            }
        }

        return precioTotal;
    }

}