package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="ticket")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idTicket;

	@OneToOne(mappedBy = "ticket", cascade = CascadeType.ALL)
	private Pago pago;
	
	private Long idUser; 
	
	private Double precio;
	
	private Date fechaDePago;
	//CAMBIAR


	public Ticket() {	}


	public Ticket (Pago pago, Long idUsuario, Date fechaDeCompra, Double MontoDeCompra) {
		super();
		this.pago=pago; 
		this.idUser=idUsuario; 
		this.fechaDePago = fechaDeCompra; 
		this.precio = MontoDeCompra;
	
		
	}

	
	
	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}




	public Pago getPago() {
		return pago;
	}


	public void setPago(Pago pago) {
		this.pago = pago;
	}


	public Date getFechaDePago() {
		return fechaDePago;
	}


	public void setFechaDePago(Date fechaDePago) {
		this.fechaDePago = fechaDePago;
	}


	public Long getIdTicket() {
		return idTicket;
	}


	public void setIdTicket(Long idTicket) {
		this.idTicket = idTicket;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}
