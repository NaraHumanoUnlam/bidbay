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
	
	
	
}
