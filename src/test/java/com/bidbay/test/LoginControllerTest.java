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
import com.bidbay.models.entity.RolUsuario;
import com.bidbay.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

import com.bidbay.controllers.LoginController;


public class LoginControllerTest {
    @Mock
    private IUsuarioService usuarioService;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoguear() {
        String viewName = loginController.loguear(model);
        assertEquals("views/login", viewName);
    }

    @Test
    public void testValidarLogin_UsuarioValido() {
        String nick = "testuser";
        String password = "testpassword";
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNick(nick);
        usuario.setRol(RolUsuario.ROL_USUARIO);

        when(usuarioService.validarUsuario(nick, password)).thenReturn(usuario);
        when(session.getAttribute("rol")).thenReturn(null);

        String viewName = loginController.validarLogin(nick, password, session, model);
        

        verify(session).setAttribute("idUsuario", usuario.getId());
        verify(session).setAttribute("logueo", usuario.getNick());
        verify(session).setAttribute("rol", "Usuario");
        verify(model).addAttribute("rol", session.getAttribute("rol"));
    }

    @Test
    public void testValidarLogin_UsuarioInvalido() {
        String nick = "testuser";
        String password = "testpassword";

        when(usuarioService.validarUsuario(nick, password)).thenReturn(null);

        String viewName = loginController.validarLogin(nick, password, session, model);

        verify(model).addAttribute("error", "Usuario y/o contraseña inválidos.");
        assertEquals("views/login", viewName);
    }
}
