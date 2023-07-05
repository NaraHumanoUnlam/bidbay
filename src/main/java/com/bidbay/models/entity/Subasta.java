package com.bidbay.models.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subastas")
public class Subasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "subastador")
    @OneToOne
    private Usuario subastador;

    @Column(name = "ofertante")
    @ManyToOne
    private Ofertante ofertante;
    
    @Column(name = "producto")
    @OneToOne
    private Producto producto;

    @Column(name = "precio_inicial")
    private BigDecimal precioInicial;
    
    
    @Column(name = "precio_maximo")
    private BigDecimal maximo;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Usuario getSubastador() {
		return subastador;
	}


	public void setSubastador(Usuario subastador) {
		this.subastador = subastador;
	}


	public Ofertante getOfertante() {
		return ofertante;
	}


	public void setOfertante(Ofertante ofertante) {
		this.ofertante = ofertante;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public BigDecimal getPrecioInicial() {
		return precioInicial;
	}


	public void setPrecioInicial(BigDecimal precioInicial) {
		this.precioInicial = precioInicial;
	}


	public BigDecimal getMaximo() {
		return maximo;
	}


	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}
    
    
}