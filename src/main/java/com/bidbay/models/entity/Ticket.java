package com.bidbay.models.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="ticket")

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idTicket;


	private Long idPago;
	
	private Double precio;
	
	//CAMBIAR
	//asociado a una compra
	private List<String> productos;
	
	//id usuario entonces
	private String nickUser;
	



	public Ticket () {
		super();
	}

	public Long getIdTicket() {
		return idTicket;
	}


	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}


	public Long getIdPago() {
		return idPago;
	}


	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public List<String> getProductos() {
		return productos;
	}


	public void setProductos(List<String> productos) {
		this.productos = productos;
	}


	public String getNickUser() {
		return nickUser;
	}


	public void setNickUser(String nickUser) {
		this.nickUser = nickUser;
	}




}
