package com.bidbay.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bidbay.models.entity.*;
import com.bidbay.service.IUsuarioService;
public class TestUsuario {
	//Todos los test sobre usuario :) 
	@Test
	public void seCreaUsuario() {
		Usuario usuario = new Usuario(3L,"prueba1","unemail@dominio.com","123456","nombre","apellido","Calle Falsa 123", "55555555");
		Assert.isTrue(usuario!=null);
	}

	@Test
	public void seCreaUsuarioConDatosSinId() {
		Usuario usuario = new Usuario("prueba2","unemail@dominio.com","123456","nombre","apellido","Calle Falsa 123", "55555555");
		Assert.isTrue(usuario!=null);
	}
	
	@Test
	public void seCreaUsuarioVacio() {
		Usuario usuario = new Usuario();
		Assert.isTrue(usuario!=null);
	}
	
}
