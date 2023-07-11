package com.bidbay.models.entity;

import java.util.List;

import jakarta.persistence.Entity;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.io.Serializable;


@Entity
@Table(name="pago")
public class Pago implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPago;
	
	//@NotEmpty
	private String DNI;
	//@NotEmpty
	private String numeroTarjeta; 
	//@NotEmpty
	private String cvc; 
	
	private String tipoDeTarjeta; 
	
	//@NotEmpty
	private String nombreDeCliente; 
	//@NotEmpty
	private String fechaVencimiento; 
	//@NotEmpty
	private String mes;
	//@NotEmpty
	private String anio;
	
	private Double precio; 	

	private String mensaje;
	
	private Boolean aprobado;
	
	@OneToOne
	@JoinColumn(name = "ticket")
	private Ticket ticket;

		
	public Pago ()
	{
		super();
	}


	public Pago( String Dni, String tarjeta,String clave, String nombre, String mes, String anio, Double precioTotal) {

		this.nombreDeCliente = nombre; 
		this.DNI = Dni;
		this.numeroTarjeta = tarjeta;
		this.cvc = clave;
		this.mes = mes; 
		this.anio = anio;
		this.fechaVencimiento = (this.mes + "/" + this.anio);
		this.precio = precioTotal;
		this.aprobado=false;
	}
	
	public Pago(String Dni, String tarjeta,String mes, String anio, String nombre, String cvc) {


		this.nombreDeCliente = nombre; 
		this.DNI = Dni;
		this.numeroTarjeta = tarjeta;
		this.cvc = cvc;
		this.mes = mes; 
		this.anio = anio;
		this.fechaVencimiento = (this.mes + "/" + this.anio);
		this.aprobado=false;
	}



	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}


	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getfechaDeVencimiento() {
		return fechaVencimiento;
	}

	public void setfechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaVencimiento = fechaDeVencimiento;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getNombreDeCliente() {
		return nombreDeCliente;
	}

	public void setNombreDeCliente(String nombreDeCliente) {
		this.nombreDeCliente = nombreDeCliente;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public String getAnio() {
		return anio;
	}


	public void setAnio(String anio) {
		this.anio = anio;
	}

	public Ticket getTicket() {
		return ticket;
	}
//revisar ticket 
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
		}
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getTipoDeTarjeta() {
		return tipoDeTarjeta;
	}


	public void setTipoDeTarjeta(String tipoDeTarjeta) {
		this.tipoDeTarjeta = tipoDeTarjeta;
	}
}//ultima llave
