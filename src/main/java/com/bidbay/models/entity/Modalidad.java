package com.bidbay.models.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "modalidad")
public class Modalidad  implements Serializable  {
	   private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotEmpty
	    private String nombre;

	    @OneToMany(mappedBy = "modalidad")
	    @Nullable
	    private List<Producto> producto;

	    public Modalidad() {
	    }

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

		public List<Producto> getProducto() {
			return producto;
		}

		public void setProducto(List<Producto> producto) {
			this.producto = producto;
		}
	    
	    
}
