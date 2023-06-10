package com.bidbay.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bidbay.controllers.ProductoController;
import com.bidbay.models.entity.Producto;
import com.bidbay.service.IProductoService;

public class ProductoControllerTest {

    @Mock
    private IProductoService myService;

    @InjectMocks
    private ProductoController myController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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
        Assert.assertTrue("Remera de prueba".equals(result));
    }
}