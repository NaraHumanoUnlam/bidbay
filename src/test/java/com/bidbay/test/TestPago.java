package com.bidbay.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import com.bidbay.models.entity.*;

public class TestPago {

    @Test
    public void seGeneraPago() {

        Pago generacionDePago = new Pago("mailCliente","DNI" , 55555555555555555L,123, "nombreDeCliente", "mes","anio", 190.3);
        
        System.out.println(generacionDePago.getUsario());
        System.out.println(generacionDePago.getPrecio());
        
        Assert.assertNotNull(generacionDePago);
    }
    
    @Test
    public void VerificarFechaDeVencimientoCon7Digitos() {
    	Pago generacionDePago = new Pago("mailCliente","DNI" , 55555555555555555L,123, "nombreDeCliente", "mm","anio", 190.3);
    	System.out.println(generacionDePago.getfechaDeVencimiento());
    	int obtenido = generacionDePago.getfechaDeVencimiento().length();
    	int esperado = 7;
    	assertEquals(esperado, obtenido);
    }
    
    @Test
    public void seGuardandatos() {
    	Pago generacionDePago = new Pago("mailCliente","DNI" , 55555555555555555L,123, "nombreDeCliente", "mes","anio", 190.3);
        
    	System.out.println(generacionDePago.getfechaDeVencimiento());
    	
    	assertNotNull(generacionDePago);
    }
    

}