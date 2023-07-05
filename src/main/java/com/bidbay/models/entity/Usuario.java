package com.bidbay.models.entity;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		@NotEmpty
		private String nick;
		@NotEmpty
		private String email;
		@NotEmpty
		private String password;
		@NotEmpty
		private String nombre;
		@NotEmpty
		private String apellido;
		@NotEmpty
		private String direccion;
		@NotEmpty
		private String telefono;
		
		private List<String> notificaciones;
		
		private List<Double> ratings;
		
		@Column(nullable = true)
		@OneToMany(fetch=FetchType.LAZY, mappedBy="id", cascade={CascadeType.ALL})
		private List<Producto> compras; 
		
		@Column(nullable = true)
		@OneToMany(fetch=FetchType.LAZY, mappedBy="id", cascade={CascadeType.ALL})
		private List<Producto> publicaciones;

		@Column(nullable = true)
		@OneToMany(fetch=FetchType.LAZY, mappedBy="id", cascade={CascadeType.ALL})
		private List<Producto> favoritos; 
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNick() {
			return nick;
		}

		public void setNick(String nick) {
			this.nick = nick;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		

		public List<String> getNotificaciones() {
			return notificaciones;
		}

		public void setNotificaciones(List<String> notificaciones) {
			this.notificaciones = notificaciones;
		}

		public Usuario(Long id, @NotEmpty String nick, @NotEmpty String email, @NotEmpty String password,
				@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String direccion,
				@NotEmpty String telefono) {
			super();
			this.id = id;
			this.nick = nick;
			this.email = email;
			this.password = password;
			this.nombre = nombre;
			this.apellido = apellido;
			this.direccion = direccion;
			this.telefono = telefono;
			this.notificaciones = new ArrayList<String>();
		}

		public Usuario(@NotEmpty String nick, @NotEmpty String email, @NotEmpty String password,
				@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String direccion,
				@NotEmpty String telefono) {
			super();
			this.nick = nick;
			this.email = email;
			this.password = password;
			this.nombre = nombre;
			this.apellido = apellido;
			this.direccion = direccion;
			this.telefono = telefono;
			this.notificaciones = new ArrayList<String>();
		}

		public Usuario() {
			super();
		}

		public void agregarNotificacion(String notificacion) {
			this.notificaciones.add(notificacion);
			
		}

		public String buscarNotificacion(String notificacion) {
			String noti = null;
			for (String string : notificaciones) {
				if(string.equals(notificacion)) {
					noti = string;
				}
			}
			return noti;
		}

		public Boolean eliminarNotificacion(String notificacion) {
			Boolean isDeleted = false;
			for (int i = 0; i < notificaciones.size(); i++) {
				if(notificacion.equals(notificaciones.get(i))) {
					notificaciones.remove(i);
					isDeleted = true;
				}
			}
			return isDeleted;
		}

		public double getRating() {
			// TODO Auto-generated method stub
			if (ratings == null || ratings.isEmpty()) {
	            return 0.0;
	        }
	        double sum = 0.0;
	        for (Double rating : ratings) {
	            sum += rating;
	        }
	        return sum / ratings.size();
		}

		public void setRatings(List<Double> ratings) {
			// TODO Auto-generated method stub
			this.ratings = ratings;
		}
		
		public void setRating(Double rating) {
			// TODO Auto-generated method stub
			if (ratings == null) {
	            ratings = new ArrayList<>();
	        }
	        ratings.add(rating);
		}

}
