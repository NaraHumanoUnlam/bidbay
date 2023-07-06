package com.bidbay.models.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ofertante")
public class Ofertante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "usuario")
	@OneToOne
	private Usuario usuario;
	@Column(name = "oferta")
	private BigDecimal oferta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public BigDecimal getOferta() {
		return oferta;
	}
	public void setOferta(BigDecimal oferta) {
		this.oferta = oferta;
	}
	
	
}