package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notificacion")
public class Notificacion implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
    @JoinColumn(name = "usuarios_id")
	private Usuario usuarios;
	
	private String titulo;
	private String notificacion;
	
	private Date fecha;
	
	private String enlace;

	public Notificacion(Long id,String titulo, String notificacion, Date fecha, Usuario user) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.notificacion = notificacion;
		this.fecha = fecha;
		this.usuarios = user;
	}

	public Notificacion(String titulo,String notificacion, Date fecha, Usuario user) {
		super();
		this.titulo = titulo;
		this.notificacion = notificacion;
		this.fecha = fecha;
		this.usuarios = user;
	}

	public Notificacion(String titulo,String notificacion, Usuario user) {
		super();
		this.titulo = titulo;
		this.notificacion = notificacion;
		this.usuarios = user;
		this.fecha = new Date(System.currentTimeMillis());
	}

	public Notificacion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	
	
	
}
