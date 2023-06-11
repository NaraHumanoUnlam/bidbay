package com.bidbay.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bidbay.models.entity.*;

public class TestCarrito {

    private Carrito carrito;

    @Before
    public void setUp() {
        carrito = new Carrito(123L);
    }

    @Test
    public void testGetCarritoItems() {
        // Arrange
        List<CarritoItem> carritoItems = new ArrayList<>();
        carritoItems.add(new CarritoItem());
        carritoItems.add(new CarritoItem());
        carrito.setCarritoItems(carritoItems);

        // Act
        List<CarritoItem> result = carrito.getCarritoItems();

        // Assert
        assertEquals(carritoItems, result);
    }

    @Test
    public void testSetCarritoItems() {
        // Arrange
        List<CarritoItem> carritoItems = new ArrayList<>();
        carritoItems.add(new CarritoItem());
        carritoItems.add(new CarritoItem());

        // Act
        carrito.setCarritoItems(carritoItems);

        // Assert
        assertEquals(carritoItems, carrito.getCarritoItems());
    }

    @Test
    public void testAddCarritoItem() {
        // Arrange
        CarritoItem carritoItem = mock(CarritoItem.class);
        when(carritoItem.getCarrito()).thenReturn(carrito);

        // Act
        carrito.addCarritoItem(carritoItem);

        // Assert
        assertTrue(carrito.getCarritoItems().contains(carritoItem));
        assertEquals(carrito, carritoItem.getCarrito());
    }

    @Test
    public void testRemoveCarritoItem() {
        // Arrange
        CarritoItem carritoItem = mock(CarritoItem.class);
        carrito.addCarritoItem(carritoItem);

        // Act
        carrito.removeCarritoItem(carritoItem);

        // Assert
        assertFalse(carrito.getCarritoItems().contains(carritoItem));
        assertNull(carritoItem.getCarrito());
    }


    @Test
    public void testGetIdUsuario() {
        // Arrange
        Long idUsuario = 123L;

        // Act
        Object result = carrito.getIdUsuario();

        // Assert
        assertEquals(idUsuario, result);
    }
}