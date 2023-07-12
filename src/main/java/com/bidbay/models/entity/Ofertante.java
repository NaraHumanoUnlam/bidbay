package com.bidbay.models.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "ofertante")
public class Ofertante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "usuarios", referencedColumnName = "id" )
	private Usuario usuario;
	@ManyToOne
	private Subasta subasta;
	@Column(name = "oferta")
	private Double oferta;
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
	public Double getOferta() {
		return oferta;
	}
	public void setOferta(Double oferta) {
		this.oferta = oferta;
	}
	
	
}
