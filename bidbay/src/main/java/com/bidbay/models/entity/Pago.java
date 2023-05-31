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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name="pago")
public class Pago {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@NotEmpty
	private Long idPago;
	@NotEmpty
	private String mailCliente; 
	@NotEmpty
	private String DNI;
	@NotEmpty
	private Integer numeroTarjeta; 
	@NotEmpty
	private Integer cvc; 

	//fecha de vencimiento
	private Date fechaVencimiento; 
	@NotEmpty
	private String mes;
	@NotEmpty
	private String anio;
	
//	private String conjuncionFecha = mes+"/"+anio;

  	public Date transcribirFecha  (String conjuncionFecha) {
  		SimpleDateFormat formatear = new SimpleDateFormat("MM/yyyy");
  		try {
			Date fechaFormateada = formatear.parse(conjuncionFecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return null; 
  	}
	//datos para ticket devolucion 
	//usuario, telefono, productos que compro, precio que pago, 

	private Usuario usario; 
	private Double precio; 
	
	public Pago ()  //vacio
	{
		super();
	}


	public Pago(@NotEmpty Long idPago, @NotEmpty String mailCliente, @NotEmpty String dNI,
			@NotEmpty Integer numeroTarjeta, @NotEmpty Integer cvc, @NotEmpty String mes,
			@NotEmpty String anio,  Usuario usario, Double precio) {
		super();
		this.idPago = idPago;
		this.mailCliente = mailCliente;
		DNI = dNI;
		this.numeroTarjeta = numeroTarjeta;
		this.cvc = cvc;
		this.fechaVencimiento = this.transcribirFecha(mes+"/"+anio);
		this.mes = mes;
		this.anio = anio;

		this.usario = usario;
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

	public Integer getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Integer numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Integer getCvc() {
		return cvc;
	}

	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}

	public Date getMesVencimiento() {
		return fechaVencimiento;
	}

	public void setMesVencimiento(Date mesVencimiento) {
		this.fechaVencimiento = mesVencimiento;
	}

	public Usuario getUsario() {
		return usario;
	}

	public void setUsario(Usuario usario) {
		this.usario = usario;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	
}
