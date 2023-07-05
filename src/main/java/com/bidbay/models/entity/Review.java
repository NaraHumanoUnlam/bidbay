package com.bidbay.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime fecha;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Producto producto;

    private String mensaje;

    private double puntaje;

	public Review(LocalDateTime fecha, Usuario usuario, Producto producto, String mensaje, double puntaje) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.producto = producto;
		this.mensaje = mensaje;
		this.puntaje = puntaje;
	}
    
}
