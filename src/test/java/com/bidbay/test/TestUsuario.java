package com.bidbay.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.*;
import com.bidbay.service.IUsuarioService;
import com.bidbay.service.UsuarioServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class TestUsuario {
	 @Mock
	    private IUsuarioDao usuarioDao;
	    
	    @InjectMocks
	    private UsuarioServiceImpl usuarioService;
	    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    
	//Todos los test sobre usuario :) 
	@Test
	public void seCreaUsuario() {
		Usuario usuario = new Usuario(3L,"prueba1","unemail@dominio.com","123456","nombre","apellido","Calle Falsa 123", "55555555");
		Assert.isTrue(usuario!=null);
	}

	@Test
	public void seCreaUsuarioConDatosSinId() {
		Usuario usuario = new Usuario("prueba2","unemail@dominio.com","123456","nombre","apellido","Calle Falsa 123", "55555555");
		Assert.isTrue(usuario!=null);
	}
	
	@Test
	public void seCreaUsuarioVacio() {
		Usuario usuario = new Usuario();
		Assert.isTrue(usuario!=null);
	}
	
	 @Test
	    public void testValidarUsuario_CredencialesCorrectas_DebeDevolverUsuarioCorrecto() {
	        // Configurar el escenario de prueba
	        Usuario usuario1 = new Usuario();
	        usuario1.setNick("usuario1");
	        usuario1.setPassword("password1");

	        Usuario usuario2 = new Usuario();
	        usuario2.setNick("usuario2");
	        usuario2.setPassword("password2");
	        
	        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
	        
	        when(usuarioDao.findAll()).thenReturn(usuarios);
	        
	        // Ejecutar el método validarUsuario()
	        Usuario resultado = usuarioService.validarUsuario("usuario1", "password1");
	        
	        // Verificar el resultado
	        Assertions.assertNotNull(resultado);
	        Assertions.assertEquals("usuario1", resultado.getNick());
	        Assertions.assertEquals("password1", resultado.getPassword());
	    }
	
	 
	    
	    @Test
	    public void testValidarUsuario_CredencialesIncorrectas_DebeDevolverNull() {
	        // Configurar el escenario de prueba
	        Usuario usuario1 = new Usuario();
	        usuario1.setNick("usuario1");
	        usuario1.setPassword("password1");

	        Usuario usuario2 = new Usuario();
	        usuario2.setNick("usuario2");
	        usuario2.setPassword("password2");
	        
	        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
	        
	        when(usuarioDao.findAll()).thenReturn(usuarios);
	        
	        // Ejecutar el método validarUsuario()
	        Usuario resultado = usuarioService.validarUsuario("usuario1", "password2");
	        
	        // Verificar el resultado
	        Assertions.assertNull(resultado);
	    }
	    
	    @Test
	    public void testFindByEmail_EmailExistente_DebeDevolverUsuarioCorrecto() {
	        // Configurar el escenario de prueba
	        Usuario usuario1 = new Usuario();
	        usuario1.setEmail("usuario1@example.com");

	        Usuario usuario2 = new Usuario();
	        usuario2.setEmail("usuario2@example.com");

	        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

	        when(usuarioDao.findAll()).thenReturn(usuarios);

	        // Ejecutar el método findByEmail() con un email existente
	        Usuario resultado = usuarioService.findByemail("usuario1@example.com");

	        // Verificar el resultado
	        Assertions.assertNotNull(resultado);
	        Assertions.assertEquals("usuario1@example.com", resultado.getEmail());
	    }
	    
	    @Test
	    public void testFindAll_DebeDevolverListaCompletaDeUsuarios() {
	        // Configurar el escenario de prueba
	        Usuario usuario1 = new Usuario();
	        usuario1.setNick("usuario1");

	        Usuario usuario2 = new Usuario();
	        usuario2.setNick("usuario2");

	        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

	        when(usuarioDao.findAll()).thenReturn(usuarios);

	        // Ejecutar el método findAll()
	        List<Usuario> resultado = usuarioService.findAll();

	        // Verificar el resultado
	        Assertions.assertEquals(2, resultado.size());
	        Assertions.assertTrue(resultado.contains(usuario1));
	        Assertions.assertTrue(resultado.contains(usuario2));
	    }
	    
	    @Test
	    public void testSave_DebeGuardarUsuarioCorrectamente() {
	        // Configurar el escenario de prueba
	        Usuario usuario = new Usuario();
	        usuario.setNick("usuario1");

	        // Ejecutar el método save()
	        usuarioService.save(usuario);

	        // Verificar que el método save() del repositorio haya sido llamado
	        verify(usuarioDao).save(usuario);
	    }
	
}
