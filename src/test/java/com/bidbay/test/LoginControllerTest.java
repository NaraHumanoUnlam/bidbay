package com.bidbay.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IUsuarioService;
import com.bidbay.controllers.LoginController;


public class LoginControllerTest {
	  	@Mock
	    private IUsuarioService usuarioService;
	        
	    @Mock
	    private Model model;
	    
	    @Mock
	    private Map <String, Object> model2;
	    
	    @Mock
	    private BindingResult bindingResult;
	    
	    @Captor
	    private ArgumentCaptor<String> stringArgumentCaptor;

	    @Captor
	    private ArgumentCaptor<Object> objectArgumentCaptor;

	    
	    @InjectMocks
	    private LoginController loginController;
	    
	    @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	    
	    @Test
	    public void testLoguear() {
	        String result = loginController.loguear(model);
	        assertEquals("views/login", result);
	    }
	    
	    @Test
	    public void testListar() {
	        String result = loginController.listar(model);
	        
	        verify(model).addAttribute("titulo", "Listado de usuarios");
	        verify(model).addAttribute("usuarios", usuarioService.findAll());
	        assertEquals("views/usuariosView", result);
	    }
	    
	    
	    @Test
	    public void testValidarLogin_UsuarioValido() {
	        // Arrange
	        Usuario usuarioMock = new Usuario();
	        when(usuarioService.validarUsuario(anyString(), anyString())).thenReturn(usuarioMock);

	        // Act
	        String result = loginController.validarLogin("username", "password", model);

	        // Assert
	        assertEquals("index", result);
	        verify(model).addAttribute("logueo", usuarioMock.getNick());
	    }
	    
	    @Test
	    public void testValidarLogin_UsuarioInvalido() {
	        // Arrange
	        when(usuarioService.validarUsuario(anyString(), anyString())).thenReturn(null);

	        // Act
	        String result = loginController.validarLogin("username", "password", model);

	        // Assert
	        assertEquals("views/login", result);
	        verify(model).addAttribute("error", "Usuario y/o contraseña inválidos.");
	    }
	    
	    @Test
	    public void testGuardar_ValidarErrors() {
	        when(bindingResult.hasErrors()).thenReturn(true);
	        
	        String result = loginController.guardar(new Usuario(), bindingResult, model);
	        
	        verify(model).addAttribute("titulo", "Registro Usuario");
	        assertEquals("views/login", result);
	    }
	    
	    
	    @Test
	    public void testGuardar_UsuarioExistente() {
	    	// Arrange
	        Usuario usuarioMock = new Usuario();
	        BindingResult bindingResultMock = mock(BindingResult.class);
	        when(bindingResultMock.hasErrors()).thenReturn(true); // Cambio en esta línea
	        when(usuarioService.validarExistenciaUsuario(anyString(), anyString())).thenReturn(usuarioMock);

	        // Act
	        String result = loginController.guardar(usuarioMock, bindingResultMock, model);
	        System.out.println(result);
	        // Assert
	        assertEquals("views/register", result);
	        verify(model).addAttribute("titulo", "Registro Usuario");
	        verify(model).addAttribute("error", "El usuario y/o email ya existe registrado.");
	        verify(usuarioService, never()).save(any(Usuario.class));
	    }
	    
	    @Test
	    public void testGuardar_SaveUsuarioSuccess() {
	        when(bindingResult.hasErrors()).thenReturn(false);
	        when(usuarioService.findByUsername(anyString())).thenReturn(null);
	        when(usuarioService.findByemail(anyString())).thenReturn(null);
	        
	        String result = loginController.guardar(new Usuario(), bindingResult, model);
	        
	        verify(usuarioService).save(any(Usuario.class));
	        assertEquals("redirect:/login", result);
	    }
	    
	    @Test
	    public void testGuardar_UsuarioNuevo() {
	        // Arrange
	        Usuario usuarioMock = new Usuario();
	        BindingResult bindingResultMock = mock(BindingResult.class);
	        when(bindingResultMock.hasErrors()).thenReturn(false);
	        when(usuarioService.validarExistenciaUsuario(anyString(), anyString())).thenReturn(null);

	        // Act
	        String result = loginController.guardar(usuarioMock, bindingResultMock, model);

	        // Assert
	        assertEquals("redirect:/login", result);
	        verify(model, never()).addAttribute(eq("error"), anyString());
	        verify(usuarioService).save(usuarioMock);
	    }
	    
	    @Test
	    public void testGuardar_SaveErrorError() {
	        when(bindingResult.hasErrors()).thenReturn(false);
	        when(usuarioService.findByUsername(anyString())).thenReturn(null);
	        when(usuarioService.findByemail(anyString())).thenReturn(null);
	        doThrow(new RuntimeException("Error al guardar el usuario")).when(usuarioService).save(any(Usuario.class));
	        
	        String result = loginController.guardar(new Usuario(), bindingResult, model);
	        
	        verify(model).addAttribute("error", "Error al guardar el usuario: Error al guardar el usuario");
	        assertEquals("views/register", result);
	    }
}
