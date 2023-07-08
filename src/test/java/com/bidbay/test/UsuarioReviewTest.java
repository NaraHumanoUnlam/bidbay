package com.bidbay.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.service.UsuarioServiceImpl;

import com.bidbay.models.entity.*;
import java.util.*;

//@RunWith(MockitoJUnitRunner.class)
public class UsuarioReviewTest {
	@Mock
    private IUsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void testPuntuarVendedor() {
    	// Arrange
        Long vendedorId = 1L;
        double rating = 4.0;
        Usuario vendedor = new Usuario();
        vendedor.setId(vendedorId);
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        // Act
        usuarioService.puntuarVendedor(vendedorId, rating);

        // Assert
        assertEquals(rating, vendedor.getRating(), 0.01);
        verify(usuarioDao, times(1)).save(vendedor);
    }

    @Test
    public void testPuntuarVendedor_NoSuchVendedor() {
        // Arrange
        Long vendedorId = 1L;
        double rating = 4.0;
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.empty());

        // Act
        usuarioService.puntuarVendedor(vendedorId, rating);

        // Assert
        verify(usuarioDao, never()).save(any(Usuario.class));
    }

    @Test
    public void testCalcularPromedioRating() {
        // Arrange
        Long vendedorId = 1L;
        Usuario vendedor = new Usuario();
        vendedor.setId(vendedorId);
        List<Double> ratings = new ArrayList<>();
        ratings.add(3.0);
        ratings.add(4.0);
        ratings.add(5.0);
        vendedor.setRatings(ratings);
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        // Act
        double promedio = usuarioService.calcularPromedioRating(vendedorId);

        // Assert
        assertEquals(4.0, promedio, 0.01);
    }

    @Test
    public void testCalcularPromedioRating_NoRatings() {
        // Arrange
        Long vendedorId = 1L;
        Usuario vendedor = new Usuario();
        vendedor.setId(vendedorId);
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        // Act
        double promedio = usuarioService.calcularPromedioRating(vendedorId);

        // Assert
        assertEquals(0.0, promedio, 0.01);
    }
    //nuevos
    @Test
    public void testPuntuarVendedor_RatingMenorACero() {
        // Arrange
        Long vendedorId = 1L;
        double rating = -1.0;
        Usuario vendedor = new Usuario();
        vendedor.setId(vendedorId);
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        // Act
        usuarioService.puntuarVendedor(vendedorId, rating);

        // Assert
        assertEquals(0.0, vendedor.getRating(), 0.01);
        verify(usuarioDao, times(1)).save(vendedor);
    }

    @Test
    public void testPuntuarVendedor_RatingMayorACinco() {
        // Arrange
        Long vendedorId = 1L;
        double rating = 6.0;
        Usuario vendedor = new Usuario();
        vendedor.setId(vendedorId);
        when(usuarioDao.findById(vendedorId)).thenReturn(Optional.of(vendedor));

        // Act
        usuarioService.puntuarVendedor(vendedorId, rating);

        // Assert
        assertEquals(5.0, vendedor.getRating(), 0.01);
        verify(usuarioDao, times(1)).save(vendedor);
    }*/

}
