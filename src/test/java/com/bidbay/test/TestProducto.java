package com.bidbay.test;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.bidbay.models.entity.*;

public class TestProducto {
	//Test para producto
	@SuppressWarnings("removal")
	@Test
	public void seCreaProductoConTodosLosDatos() {
		Producto producto = new Producto(3L,"Remera de prueba", "esto es una prueba para los TDD", 2000.00,45,"foto.jpg");
		Assert.isTrue(producto!=null);
	}

	@SuppressWarnings("removal")
	@Test
	public void seCreaUsuarioConDatosSinId() {
		Producto producto = new Producto("Remera de prueba", "esto es una prueba para los TDD", 2000.00,45,"foto.jpg");
		Assert.isTrue(producto!=null);
	}
	
	@SuppressWarnings("removal")
	@Test
	public void seCreaUsuarioVacio() {
		Producto producto = new Producto();
		Assert.isTrue(producto!=null);
	}
}
