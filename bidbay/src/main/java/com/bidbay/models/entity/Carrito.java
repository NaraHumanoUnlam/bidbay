package com.bidbay.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carrito")
public class Carrito {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;
	
	//@NotNull
	private Long idUsuario;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "publicaciones", referencedColumnName = "id")
	private Publicacion publicacion;
	
	@NotNull
	private Integer cantidadProductos;
	
	public Carrito(Long idUsuario, Publicacion publicacion, Integer cantidadProductos) {
		super();
		this.idUsuario = idUsuario;
		this.publicacion = publicacion;
		this.cantidadProductos = cantidadProductos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}


	public @NotNull Publicacion getPublicacion() {
		return publicacion;
	}


	public Integer getCantidadProductos() {
		return cantidadProductos;
	}

	public void setCantidadProductos(Integer cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
	}
	
	
}
