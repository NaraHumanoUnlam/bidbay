package com.bidbay.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

//import org.junit.Test;
import org.springframework.util.Assert;
import com.bidbay.models.entity.Pago;

public class TestPago {

	@Test 
	public void seGeneraPago () {
		
		Pago generacionDePago = new Pago("jj@patricia.com", "33444555",4545000008888L, 222, "10", "2002" , "Patricia G","usuario",10.9);

		System.out.println(generacionDePago.getfechaDeVencimiento());
		System.out.println(generacionDePago.getNombreDeCliente());
		System.out.println(generacionDePago.getDNI());
		System.out.println(generacionDePago.getUsario());
		System.out.println(generacionDePago.getNumeroTarjeta());
	
		Assert.notNull(generacionDePago);
		
	}

}

