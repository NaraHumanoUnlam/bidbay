package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="ticket")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idTicket;


	private Long idPago;
	
	private Long idUser; 
	
	private Double precio;
	
	private Date fechaDeCompra;
	//CAMBIAR


	public Ticket() {	}


	public Ticket (Long idPago, Long idUsuario, Date fechaDeCompra, Double MontoDeCompra) {
		super();
		this.idPago=idPago; 
		this.idUser=idUsuario; 
		this.fechaDeCompra = fechaDeCompra; 
		this.precio = MontoDeCompra;
	
		
	}

	
	
	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}


	public Date getFechaDeCompra() {
		return fechaDeCompra;
	}


	public void setFechaDeCompra(Date fechaDeCompra) {
		this.fechaDeCompra = fechaDeCompra;
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
}
