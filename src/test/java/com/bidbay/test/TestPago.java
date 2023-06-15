package com.bidbay.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import com.bidbay.models.entity.*;

public class TestPago {

    @Test
    public void seGeneraPago() {

        Pago generacionDePago = new Pago("Usuario", 190.3);
        
        System.out.println(generacionDePago.getUsario());
        System.out.println(generacionDePago.getPrecio());
        
        Assert.assertNotNull(generacionDePago);
    }
    
    @Test
    public void VerificarFechaDeVencimientoCon7Digitos() {
    	Pago generacionDePago = new Pago("Usuario", 190.3);
    	generacionDePago.guardarDatos(null, null, null, null, null,"02","2025");
    	int obtenido = generacionDePago.getfechaDeVencimiento().length();
    	int esperado = 7;
    	assertEquals(esperado, obtenido);
    }
    
    @Test
    public void seGuardandatos() {
    	Pago generacionDePago = new Pago("Usuario", 190.3);
    	generacionDePago.guardarDatos("mail@gg.com", "40123789", 4545000088887777L, 123, "Roberto","02","2025");
    	System.out.println(generacionDePago.getfechaDeVencimiento());
    	
    	assertNotNull(generacionDePago);
    }
    

}