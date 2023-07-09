package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idReview;

	private Date fecha;

	@ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private String mensaje;

    public Long getIdReview() {
		return idReview;
	}

	public void setIdReview(Long idReview) {
		this.idReview = idReview;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	private double puntaje;
    
    public Review() {}

	public Review(Date fecha, Usuario usuario, Producto producto, String mensaje, double puntaje) {
		super();
		this.fecha = fecha;
		this.usuario = usuario;
		this.producto = producto;
		this.mensaje = mensaje;
		this.puntaje = puntaje;
	}

	public double getPuntaje() {
		return puntaje;
	}

    
}
