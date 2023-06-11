package com.bidbay.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bidbay.models.entity.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

@RunWith(MockitoJUnitRunner.class)
public class TestCarritoItem {

    @Mock
    private Producto mockProducto;
    

    @Test
    public void testGetSetCantidadProductos() {
        // Arrange
        Integer cantidadProductos = 5;
        CarritoItem carritoItem = new CarritoItem();

        // Act
        carritoItem.setCantidadProductos(cantidadProductos);
        Integer result = carritoItem.getCantidadProductos();

        // Assert
        assertEquals(cantidadProductos, result);
    }

    @Test
    public void testGetSetProducto() {
        // Arrange
        CarritoItem carritoItem = new CarritoItem();

        // Act
        carritoItem.setProducto(mockProducto);
        Producto result = carritoItem.getProducto();

        // Assert
        assertEquals(mockProducto, result);
    }

    @Test
    public void testGetSetCarrito() {
        // Arrange
        CarritoItem carritoItem = new CarritoItem();
        Carrito mockCarrito = new Carrito();

        // Act
        carritoItem.setCarrito(mockCarrito);
        Carrito result = carritoItem.getCarrito();

        // Assert
        assertEquals(mockCarrito, result);
    }

    @Test
    public void testGetSetStock() {
        // Arrange
        Integer stock = 10;
        CarritoItem carritoItem = new CarritoItem();

        // Act
        carritoItem.setStock(stock);
        Integer result = carritoItem.getStock();

        // Assert
        assertEquals(stock, result);
    }

    
    @Test
    public void testGetIdItem() throws Exception {
        // Arrange
        CarritoItem carritoItem = new CarritoItem();
        Field idItemField = CarritoItem.class.getDeclaredField("idItem");
        idItemField.setAccessible(true);
        idItemField.set(carritoItem, 123L);

        // Act
        Long result = carritoItem.getIdItem();

        // Assert
        assertNotNull(result);
    }
}