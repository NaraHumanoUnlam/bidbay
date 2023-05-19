package com.bidbay.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "publicaciones")
public class Publicacion implements Serializable, Comparable<Publicacion> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numeroDePublicacion;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date createAt;
	
	
	//fijate en esto juli!!
	@OneToOne
	@JoinColumn(name = "productos", referencedColumnName = "id")
	private Producto producto;

	private Usuario usuario; // Nullable x ahora

	public Integer getNumeroDePublicacion() {
		return numeroDePublicacion;
	}

	public Publicacion() {
		super();
	}

	public Publicacion(Integer numeroDePublicacion, @NotNull Date createAt, Producto producto) {
		super();
		this.numeroDePublicacion = numeroDePublicacion;
		this.createAt = createAt;
		this.producto = producto;
	}

	public Publicacion(Long id, Integer numeroDePublicacion, @NotNull Date createAt, Producto producto) {
		super();
		this.id = id;
		this.numeroDePublicacion = numeroDePublicacion;
		this.createAt = createAt;
		this.producto = producto;
	}

	public void setNumeroDePublicacion(Integer numeroDePublicacion) {
		this.numeroDePublicacion = numeroDePublicacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int compareTo(Publicacion other) {
		double precioThis = this.getProducto().getPrecio();
		double precioOther = other.getProducto().getPrecio();

		if (precioThis < precioOther) {
			return -1;
		} else if (precioThis > precioOther) {
			return 1;
		} else {
			return 0;
		}
	}

	public Long getId() {
		return id;
	}
}
