package com.bidbay.models.entity;

import java.util.List;

import jakarta.persistence.Entity;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;//
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name="pago")
public class Pago implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPago;
	
	//@NotEmpty
	private String mailCliente; 
	//@NotEmpty
	private String DNI;
	//@NotEmpty
	private Long numeroTarjeta; 
	//@NotEmpty
	private Integer cvc; 
	//@NotEmpty
	private String nombreDeCliente; 
	//@NotEmpty
	private String fechaVencimiento; 
	//@NotEmpty
	private String mes;
	//@NotEmpty
	private String anio;
	
	private Double precio; 	

	private String nombreUsario; 
	private String ticket;
	private Boolean aprobado=false;;
	
	public Pago ()  //vacio
	{
		super();
	}

//	public Pago (String nickUsuario, double precio)  //vacio
//	{
//		super();
//		this.nombreUsario = nickUsuario;
//		this.precio = precio;
//	}

	public Pago( String mail, String Dni, Long tarjeta,Integer clave, String nombre, String mes, String anio, Double precioTotal) {


		this.mailCliente = mailCliente;
		this.nombreDeCliente = nombre; 
		this.DNI = Dni;
		this.numeroTarjeta = tarjeta;
		this.cvc = cvc;
		this.mes = mes; 
		this.anio = anio;
		this.fechaVencimiento = (this.mes + "/" + this.anio);
		this.precio = precioTotal;
	}


	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public String getMailCliente() {
		return mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Integer getCvc() {
		return cvc;
	}

	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}

	public String getfechaDeVencimiento() {
		return fechaVencimiento;
	}

	public void setfechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaVencimiento = fechaDeVencimiento;
	}

	public String getUsario() {
		return nombreUsario;
	}

	public void setUsario(String usario) {
		this.nombreUsario = usario;
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

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
		}
	
}
