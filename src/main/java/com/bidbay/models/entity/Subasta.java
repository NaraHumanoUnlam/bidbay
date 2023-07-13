package com.bidbay.models.entity;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import jakarta.persistence.*;

@Entity
@Table(name = "subastas")
public class Subasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @OneToOne
	@JoinColumn(name = "usuarios", referencedColumnName = "id" )
    private Usuario subastador;

    @Nullable
    @Column(name = "ofertante")
    @OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL)
    private List<Ofertante> ofertantes;
    
    @Nullable
    @OneToOne
	@JoinColumn(name = "productos", referencedColumnName = "id" )
    private Producto producto;

    @Column(name = "precio_inicial")
    private Double precioInicial;
    
    @Nullable
    @Column(name = "precio_maximo")
    private Double maximo;
    
    private LocalDateTime fechaLimite;


	public Subasta() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getSubastador() {
		return subastador;
	}


	public void setSubastador(Usuario subastador) {
		this.subastador = subastador;
	}

	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Double getPrecioInicial() {
		return precioInicial;
	}


	public void setPrecioInicial(Double precioInicial) {
		this.precioInicial = precioInicial;
	}


	public Double getMaximo() {
		return maximo;
	}


	public void setMaximo(Double maximo) {
		this.maximo = maximo;
	}


	public List<Ofertante> getOfertantes() {
		return ofertantes;
	}


	public void setOfertantes(List<Ofertante> ofertantes) {
		this.ofertantes = ofertantes;
	}


	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}


	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}


    
    
}