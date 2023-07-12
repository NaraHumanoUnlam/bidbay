package com.bidbay.models.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carrito_item")
public class CarritoItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;
	
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "productos", referencedColumnName = "id")
	private Producto producto;
	
	@NotNull
	private Integer cantidadProductos;
	
	@ManyToOne
    @JoinColumn(name = "carritoId")
    private Carrito carrito;
	
	public CarritoItem(Producto producto, Integer cantidadProductos) {
		super();
		this.producto = producto;
		this.cantidadProductos = cantidadProductos;
	}
	
	public CarritoItem() {}


	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public Long getIdItem() {
		return idItem;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getStock() {
		// TODO Auto-generated method stub
		return this.cantidadProductos;
	}

	public void setStock(Object stock) {
		// TODO Auto-generated method stub
		this.cantidadProductos = (@NotNull Integer) stock;
	}
	
	public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
	
}