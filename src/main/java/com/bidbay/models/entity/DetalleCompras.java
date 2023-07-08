package com.bidbay.models.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_compras")
public class DetalleCompras implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;
	
	private int cantidad;
	
	@Column(name = "precio_compra")
	private Double precioCompra;
	
	@Column(name = "precio_unitario")
	private Double precioUnitario;

	public Double getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	@NotNull
	@OneToOne
	@JoinColumn(name = "productos", referencedColumnName = "id")
	private Producto producto;

	
	@ManyToOne
    private Compras compra;
	
	public DetalleCompras() {}
	

    public DetalleCompras(Producto producto, Compras compras) {
    	this.producto = producto;
    	this.compra = compras;
	}

	public Compras getCompra() {
		return compra;
	}
	public void setCompra(Compras compra) {
		this.compra = compra;
	}
	
    
    	
	public Long getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
