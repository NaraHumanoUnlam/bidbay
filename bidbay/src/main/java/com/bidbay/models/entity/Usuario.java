package com.bidbay.models.entity;
import java.io.Serializable;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

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
		}

		public Usuario() {
			super();
		}

		
		
}