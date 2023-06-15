package com.bidbay.test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import org.mockito.junit.MockitoJUnitRunner;

	import com.bidbay.models.dao.IPagoDao;
	import com.bidbay.models.entity.Carrito;
	import com.bidbay.models.entity.Pago;
	import com.bidbay.models.entity.Usuario;
import com.bidbay.service.PagoServiceImpl;

	@RunWith(MockitoJUnitRunner.class)
	public class TestPagoMock {

	    @Mock
	    private IPagoDao pagoDao;

	    @InjectMocks
	    private PagoServiceImpl pagoService;

	    @Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testGenerarPago() {
	    	Long idUsuario = 1L;
	        Carrito carrito = new Carrito(idUsuario);
	        Usuario usuario = new Usuario();
	        usuario.setNick("usuario1");

	        when(pagoDao.save(any(Pago.class))).thenReturn(new Pago());

	        Long pagoId = pagoService.generarPago(carrito);

	        assertNotNull(pagoId);
	        verify(pagoDao, times(1)).save(any(Pago.class));
	    }


	}

