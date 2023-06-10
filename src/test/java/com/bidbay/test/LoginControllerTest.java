package com.bidbay.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

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
    
    @SuppressWarnings("removal")
	@Test
    public void testLoguear() {
        String result = loginController.loguear(model);
        Assert.isTrue("views/login".equals(result));
    }
    
    @SuppressWarnings("removal")
	@Test
    public void testListar() {
        String result = loginController.listar(model);
        
        verify(model).addAttribute("titulo", "Listado de usuarios");
        verify(model).addAttribute("usuarios", usuarioService.findAll());
        Assert.isTrue("views/usuariosView".equals(result));
    }
    
    
	@Test
	public void testValidarLogin_UsuarioValido() {
		 HttpSession session = Mockito.mock(HttpSession.class);
		// Crear un usuario válido
		Usuario usuario = new Usuario();
		usuario.setNick("testuser");
		usuario.setPassword("testpassword");

		// Mock del usuario buscado
		Usuario usuarioBuscado = mock(Usuario.class);

		// Configurar el servicio de usuario para devolver el usuario buscado
		when(usuarioService.validarUsuario(usuario.getNick(), usuario.getPassword())).thenReturn(usuarioBuscado);

		// Llamar al método validarLogin del controlador
		String result = loginController.validarLogin(usuario.getNick(), usuario.getPassword(), session, model);

		// Verificar que establece la variable de sesión y devuelve la vista esperada
		verify(session).setAttribute("idUsuario", usuarioBuscado.getId());
		verify(model).addAttribute("logueo", usuarioBuscado.getNick());
		assertEquals("index", result);
	}

    
    @Test
    public void testValidarLogin_UsuarioInvalido() {
    	 HttpSession session = Mockito.mock(HttpSession.class);
        // Arrange
        when(usuarioService.validarUsuario(anyString(), anyString())).thenReturn(null);

        // Act
        String result = loginController.validarLogin("username", "password",session, model);

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
	public void testGuardar_ConUsuarioExistente() {
		// Crear un usuario con datos existentes
		Usuario usuario = new Usuario();
		usuario.setNick("existinguser");
		usuario.setEmail("existinguser@example.com");

		// Configurar el servicio de usuario para devolver un usuario existente
		when(usuarioService.validarExistenciaUsuario(usuario.getNick(), usuario.getEmail())).thenReturn(usuario);

		// Llamar al método guardar del controlador
		String result = loginController.guardar(usuario, bindingResult, model);

		// Verificar que agrega el mensaje de error al modelo y devuelve la vista esperada
		verify(model).addAttribute("titulo", "Registro Usuario");
		verify(model).addAttribute("error", "El usuario y/o email ya existe registrado.");
		assertEquals("views/register", result);
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
