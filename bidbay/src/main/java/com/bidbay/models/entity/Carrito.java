package com.bidbay.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carrito")
public class Carrito implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;
	
	//@NotNull
	private Long idUsuario;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "productos", referencedColumnName = "id")
	private Producto producto;
	
	@NotNull
	private Integer cantidadProductos;
	
	public Carrito(Long idUsuario, Producto producto, Integer cantidadProductos) {
		super();
		this.idUsuario = idUsuario;
		this.producto = producto;
		this.cantidadProductos = cantidadProductos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
