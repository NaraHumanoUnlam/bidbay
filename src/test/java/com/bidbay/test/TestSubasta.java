package com.bidbay.test;

import org.junit.Assert;
import org.junit.Test;
import com.bidbay.models.entity.*;
import java.util.HashMap;
import java.util.Map;


public class TestSubasta {
	
	@Test
	public void seCreaSubasta() {
		Subasta subastaNueva = new Subasta();
		subastaNueva.setId(1l);
		Long ve = 1l;
		Assert.assertEquals(ve,subastaNueva.getId());
	}

	@Test
	public void seCreaSubastaYSeAgregaProducto() {
		Subasta subastaNueva = new Subasta();
		subastaNueva.setId(1l);
		Producto producto = new Producto("Campera","Campera de los rolling Stones",250000.00,1,"imagen.jpg");
		subastaNueva.setProducto(producto);
		
		String nombreProducto = producto.getNombre();
		Assert.assertEquals(nombreProducto, subastaNueva.getProducto().getNombre());
	}
	
	@Test
	public void seCreaSubastaConProductoyAsignaUsuario() {
		Subasta subastaNueva = new Subasta();
		subastaNueva.setId(1l);
		Producto producto = new Producto("Campera","Campera de los rolling Stones",250000.00,1,"imagen.jpg");
		subastaNueva.setProducto(producto);
		Usuario subastador = new Usuario("usuario","unmail@gmail.com","pass","usuario","de prueba","calle falsa 123","55555555");
		subastaNueva.setSubastador(subastador);
		String nombreSubastador = subastador.getNombre();
		Assert.assertEquals(nombreSubastador, subastaNueva.getSubastador().getNombre());
		
	}
	
	public void seCreaSubastaYseOferta() {
		Subasta subastaNueva = new Subasta();
		subastaNueva.setId(1l);
		Producto producto = new Producto("Campera","Campera de los rolling Stones",250000.00,1,"imagen.jpg");
		subastaNueva.setProducto(producto);
		Usuario subastador = new Usuario("usuario","unmail@gmail.com","pass","usuario","de prueba","calle falsa 123","55555555");
		subastaNueva.setSubastador(subastador);
		Usuario ofertante = new Usuario("ofertante","unmail1@gmail.com","pass","soy","el que oferta","calle falsa 456","666666666");
		Double oferta = 1.00;
		subastaNueva.agregarOfertante(ofertante,oferta);
		Assert.assertEquals(ofertante, subastaNueva.obtenerOfertantePorOferta(oferta));
		
	}
}
