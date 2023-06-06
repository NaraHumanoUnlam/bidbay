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
	
	private String nombreUsario; 
	
	private Double precio; 


	//datos para ticket devolucion 
	//usuario, telefono, productos que compro, precio que pago, 


	


	public Pago ()  //vacio
	{
		super();
	}


	public Pago( @NotEmpty String mailCliente, @NotEmpty String dNI,
			@NotEmpty Long numeroTarjeta, @NotEmpty Integer cvc, @NotEmpty String mes,
			@NotEmpty String anio, @NotEmpty String nombreCliente,   String usario, Double precio) {
		super();

		this.mailCliente = mailCliente;
		this.nombreDeCliente = nombreCliente; 
		this.DNI = dNI;
		this.numeroTarjeta = numeroTarjeta;
		this.cvc = cvc;
		this.mes = mes; 
		this.anio = anio;
		this.fechaVencimiento = (this.mes + "/" + this.anio);

		this.nombreUsario = usario;
		this.precio = precio;
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

	
	
}
