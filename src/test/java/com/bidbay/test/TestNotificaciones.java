package com.bidbay.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Usuario;
import com.bidbay.service.UsuarioServiceImpl;

public class TestNotificaciones {
    @Mock
    private IUsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void seCreaUsuarioySeLeasignaUnaNotificacion() {
        Usuario usuario = new Usuario(3L, "prueba1", "unemail@dominio.com", "123456", "nombre", "apellido",
                "Calle Falsa 123", "55555555");
        String notificacion = "una nueva notificacion";

        usuario.agregarNotificacion(notificacion);
        Assert.assertEquals(notificacion, usuario.getNotificaciones().get(0));
    }
    
    @Test
    public void seCreaUsuarioySeConsultaNotificacion() {
        Usuario usuario = new Usuario(3L, "prueba1", "unemail@dominio.com", "123456", "nombre", "apellido",
                "Calle Falsa 123", "55555555");
        String notificacion = "una nueva notificacion";

        usuario.agregarNotificacion(notificacion);
        
        String result =  usuario.buscarNotificacion(notificacion);
        Assert.assertEquals(notificacion,result);
    }
    
    @Test
    public void seCreaUsuarioyEliminaNotificacion() {
        Usuario usuario = new Usuario(3L, "prueba1", "unemail@dominio.com", "123456", "nombre", "apellido",
                "Calle Falsa 123", "55555555");
        String notificacion = "una nueva notificacion";

        usuario.agregarNotificacion(notificacion);
        
        Boolean result =  usuario.eliminarNotificacion(notificacion);
        Assert.assertEquals(true,result);
    }
    
    @Test
    public void seCreaUsuarioyListaTodasLasNotificaciones() {
        Usuario usuario = new Usuario(3L, "prueba1", "unemail@dominio.com", "123456", "nombre", "apellido",
                "Calle Falsa 123", "55555555");
        String notificacion = "una nueva notificacion";
        String notificacion2 = "una nueva notificacion nueva";
        String notificacion3 = "una nueva notificacion nueva nueva";

        usuario.agregarNotificacion(notificacion);
        usuario.agregarNotificacion(notificacion2);
        usuario.agregarNotificacion(notificacion3);
        
        
        Assert.assertEquals(3, usuario.getNotificaciones().size());
    }
    
}