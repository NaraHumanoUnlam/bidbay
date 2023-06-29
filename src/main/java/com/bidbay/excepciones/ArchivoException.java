package com.bidbay.excepciones;

public class ArchivoException extends RuntimeException {
    public ArchivoException(String message, Throwable cause) {
        super(message, cause);
    }
}