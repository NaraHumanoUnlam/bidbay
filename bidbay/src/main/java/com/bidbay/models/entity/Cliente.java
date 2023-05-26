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
import java.util.HashSet;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@NotEmpty
	private Long idCliente;
	@NotEmpty
	private String DNI; 
	@NotEmpty
	//private HashSet<String> medioDePago = new HashSet<String>(); 
	private String medioDePago; 
	
	@NotEmpty
	private Usuario usario; 
	
	
	public Cliente ()  //vacio
	{
		super();
	}
	
	public Cliente (String DeEneI,String mediosDePago, Usuario user ) { //sin ID
		this.DNI=DeEneI; 
		
		//this.medioDePago.add(mediosDePago);
		this.usario = user; 
	}

	public Cliente(Long idCliente, String dNI, String medioDePago, Usuario usario) { //Completo
		super();
		this.idCliente = idCliente;
		DNI = dNI;
	    this.medioDePago = medioDePago;
		this.usario = usario;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Usuario getUsario() {
		return usario;
	}

	public void setUsario(Usuario usario) {
		this.usario = usario;
	}
	
}
