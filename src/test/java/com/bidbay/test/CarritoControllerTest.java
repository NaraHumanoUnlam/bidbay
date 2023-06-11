package com.bidbay.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.*;
import org.mockito.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bidbay.controllers.CarritoController;
import com.bidbay.service.ICarritoService;

import jakarta.servlet.http.HttpSession;

import com.bidbay.models.entity.*;
import java.util.ArrayList;

public class CarritoControllerTest {

    @InjectMocks
    private CarritoController carritoController;

    @Mock
    private ICarritoService carritoService;
    
    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        session = mock(HttpSession.class);
    }

    @Test
    public void testListar() {
    	// Arrange
        Long idUsuario = 123L;
        Carrito carrito = new Carrito(idUsuario);
        carrito.setCarritoItems(new ArrayList<>());
        session.setAttribute("idUsuario", idUsuario);
        when(session.getAttribute("idUsuario")).thenReturn(idUsuario);
        when(carritoService.findOneByUserID(idUsuario)).thenReturn(carrito);
        double precioTotal = 0.0;

        // Act
        String viewName = carritoController.listar(model, session);

        // Assert
        assertEquals(viewName, "views/carritoView");
        verify(model).addAttribute("titulo", "Listado de carrito");
        verify(model).addAttribute("carrito", carrito);
        verify(carritoService).calcularPrecioTotal(idUsuario);
        verify(model).addAttribute("precioTotal", precioTotal);
    }

    @Test
    public void testAgregarProductoAlCarrito() {
        // Arrange
        Long idUsuario = 123L;
        Long idProducto = 456L;
        session.setAttribute("idUsuario", idUsuario);
        when(session.getAttribute("idUsuario")).thenReturn(idUsuario);

        // Act
        String viewName = carritoController.agregarProductoAlCarrito(idProducto, session, redirectAttributes);

        // Assert
        assertEquals("redirect:/carrito/listar", viewName);
        verify(carritoService).addProductToCarrito(idUsuario, idProducto, redirectAttributes);
    }

    @Test
    public void testEditar() {
        // Arrange
        Long idUsuario = 123L;
        Long idItem = 789L;
        int stock = 2;
        when(session.getAttribute("idUsuario")).thenReturn(idUsuario);

        // Act
        String viewName = carritoController.editar(idItem, session, model, stock, redirectAttributes);

        // Assert
        assertEquals("redirect:/carrito/listar", viewName);
        verify(carritoService).editCarritoItem(idUsuario, idItem, stock, redirectAttributes);
    }

    @Test
    public void testEliminarProductoDeCarrito() {
        // Arrange
        Long idUsuario = 123L;
        Long idItem = 789L;
        when(session.getAttribute("idUsuario")).thenReturn(idUsuario);

        // Act
        String viewName = carritoController.eliminarProductoDeCarrito(idItem, session, redirectAttributes);

        // Assert
        assertEquals("redirect:/carrito/listar", viewName);
        verify(carritoService).deleteCarritoItem(idUsuario, idItem, redirectAttributes);
    }

    @Test
    public void testChequearQueElUsuarioEsteLogeado_UsuarioLogeado() {
        // Arrange
        when(session.getAttribute("idUsuario")).thenReturn(123L);

        // Act
        boolean result = carritoController.chequearQueElUsuarioEsteLogeado(session);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testChequearQueElUsuarioEsteLogeado_UsuarioNoLogeado() {
        // Arrange
        when(session.getAttribute("idUsuario")).thenReturn(null);

        // Act
        boolean result = carritoController.chequearQueElUsuarioEsteLogeado(session);

        // Assert
        assertFalse(result);
    }
}