package com.bidbay.test;

import org.junit.Assert;
import org.junit.Test;
import com.bidbay.models.entity.*;

public class TestPago {

    @Test
    public void seGeneraPago() {

        Pago generacionDePago = new Pago("jj@patricia.com", "33444555", 4545000008888L, 222, "10", "2002", "Patricia G", "usuario", 10.9);

        System.out.println(generacionDePago.getfechaDeVencimiento());
        System.out.println(generacionDePago.getNombreDeCliente());
        System.out.println(generacionDePago.getDNI());
        System.out.println(generacionDePago.getUsario());
        System.out.println(generacionDePago.getNumeroTarjeta());

        Assert.assertNotNull(generacionDePago);
    }
}