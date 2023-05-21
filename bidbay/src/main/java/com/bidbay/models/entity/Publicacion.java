package com.bidbay.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable, Comparable<Publicacion> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numero;

	@OneToOne
	@JoinColumn(name = "productos", referencedColumnName = "id")
	private Producto producto;
	
	public void setNumero(Integer numeroDePublicacion) {
		this.numero = numeroDePublicacion;
	}

	public Integer getNumero() {
		return numero;
	}

	public Publicacion() {
		super();
	}

	public Publicacion(Integer numeroDePublicacion, Producto producto) {
		super();
		this.numero = numeroDePublicacion;
		this.producto = producto;
	}

	public Publicacion(Long id, Integer numeroDePublicacion, Producto producto) {
		super();
		this.id = id;
		this.numero = numeroDePublicacion;
		this.producto = producto;
	}


	public Producto getProducto() {
		return producto;
	}
 
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int compareTo(Publicacion other) {
		double precioThis = this.getProducto().getPrecio();
		double precioOther = other.getProducto().getPrecio();

		if (precioThis < precioOther) {
			return -1;
		} else if (precioThis > precioOther) {
			return 1;
		} else {
			return 0;
		}
	}

	public Long getId() {
		return id;
	}
}
