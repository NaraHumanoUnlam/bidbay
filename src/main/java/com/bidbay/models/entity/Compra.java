package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="compra")
public class Compra implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	private Date fecha;
	private Boolean estadoCompra;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Cliente cliente;
	@OneToOne(cascade = CascadeType.ALL)
	private Carrito carrito;
	
	public Compra(Long id, String codigo, Date fecha, Boolean estadoCompra, Carrito carrito) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.fecha = fecha;
		this.estadoCompra = estadoCompra;
		//this.cliente = cliente;
		this.carrito = carrito;
	}
	
	

	public Compra(String codigo, Date fecha, Boolean estadoCompra, Carrito carrito) {
	super();
	this.codigo = codigo;
	this.fecha = fecha;
	this.estadoCompra = estadoCompra;
	this.carrito = carrito;
}
	

	public Compra() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}
	
	

	public Boolean getProcesada() {
		return estadoCompra;
	}

	public void setProcesada(Boolean estadoCompra) {
		this.estadoCompra = estadoCompra;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	
}
