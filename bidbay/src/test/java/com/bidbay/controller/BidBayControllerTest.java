package com.bidbay.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bidbay.controllers.ProductoController;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.IProductoService;

@RunWith(MockitoJUnitRunner.class)
public class BidBayControllerTest {

	@Mock
    private IProductoService myService;

    @InjectMocks
    private ProductoController myController;
    
    @Test
    public void testMethod() {
        Producto productoMock = new Producto();
		// Configurar el comportamiento del mock
        when(myController.someMethod()).thenReturn(productoMock );
        
        // Ejecutar el método de prueba
        String result = Producto.methodUnderTest();
        
        // Verificar el resultado
        assertEquals("test", result);
        
        // Verificar si el método en el mock se llamó correctamente
        verify(myController).someMethod();
    }
}
