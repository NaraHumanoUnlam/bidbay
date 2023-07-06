package com.bidbay.models.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "operacioncv")
public class OperacionCV {
	
	@Id
	private Long compra;
	private Long pago;
	private Long usuarioCompra;
	private Date fecha;
	private int cantidad;
	private Double precioCompra;
	private String producto;
	private Long usuarioVende;
	public Long getCompra() {
		return compra;
	}
	public Long getPago() {
		return pago;
	}
	public Long getUsuarioCompra() {
		return usuarioCompra;
	}
	public Date getFecha() {
		return fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	
	public Double getPrecioCompra() {
		return precioCompra;
	}
	public String getProducto() {
		return producto;
	}
	public Long getUsuarioVende() {
		return usuarioVende;
	}
	

	

}
