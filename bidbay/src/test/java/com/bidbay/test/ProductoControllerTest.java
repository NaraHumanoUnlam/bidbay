package com.bidbay.test;

import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bidbay.controllers.ProductoController;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.IProductoService;


@RunWith(MockitoJUnitRunner.class)
public class ProductoControllerTest {

	@Mock
    private IProductoService myService;

    @InjectMocks
    private ProductoController myController;
    
    @SuppressWarnings("removal")
	@Test
    public void pruebaDeProductoController() {
    	Producto productoMock = Mockito.mock(Producto.class);

    	// Definir el comportamiento del mock
    	Mockito.when(productoMock.getId()).thenReturn(1L);
    	Mockito.when(productoMock.getNombre()).thenReturn("Remera de prueba");
    	Mockito.when(productoMock.getDescripcion()).thenReturn("Esto es una prueba para los TDD");
    	Mockito.when(productoMock.getPrecio()).thenReturn(2000.00);
    	Mockito.when(productoMock.getStock()).thenReturn(45);

    	// Utilizar el objeto mock en tus pruebas o código
    	System.out.println(productoMock.getNombre()); // Imprimirá "Remera de prueba"
    	System.out.println(productoMock.getPrecio()); // Imprimirá 2000.00
    	
    	  String result = productoMock.getNombre();

          // Verifica el resultado esperado
    	  Assert.isTrue("Remera de prueba".equals(result));
    }
}
