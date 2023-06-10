package com.bidbay.test;


import org.junit.Assert;
import org.junit.Test;

import com.bidbay.models.entity.*;

public class TestProducto {
	//Test para producto
	@Test
	public void seCreaProductoConTodosLosDatos() {
		Producto producto = new Producto(3L,"Remera de prueba", "esto es una prueba para los TDD", 2000.00,45,"foto.jpg");
		Assert.assertNotNull(producto);
	}

	@Test
	public void seCreaUsuarioConDatosSinId() {
		Producto producto = new Producto("Remera de prueba", "esto es una prueba para los TDD", 2000.00,45,"foto.jpg");
		Assert.assertNotNull(producto);
	}
	
	@Test
	public void seCreaUsuarioVacio() {
		Producto producto = new Producto();
		Assert.assertNotNull(producto);
	}
}
