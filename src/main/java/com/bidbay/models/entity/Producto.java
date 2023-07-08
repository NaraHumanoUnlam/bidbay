package com.bidbay.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="productos")
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String descripcion;
	
	@Nullable
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	private Double precio;

	@NotNull
	private Integer stock;
	
	private String imagen;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<Review> reviews;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario vendedor;

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    
	    Producto other = (Producto) obj;
	    
	    return id != null && id.equals(other.id);
	}

	public Producto(Long id, @NotEmpty String nombre, @NotEmpty String descripcion, @NotNull Double precio,
			@NotNull Integer stock, @NotEmpty String imagen ) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
	}

	public Producto(@NotEmpty String nombre, @NotEmpty String descripcion, @NotNull Double precio,
			@NotNull Integer stock, @NotEmpty String imagen) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
	}

	
	public Producto(@NotEmpty String nombre, @NotEmpty String descripcion, Categoria categoria, @NotNull Double precio,
			@NotNull Integer stock, String imagen) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
	}
	

	public Producto(Long id, @NotEmpty String nombre, @NotEmpty String descripcion, Categoria categoria,
			@NotNull Double precio, @NotNull Integer stock, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
	}

	public Producto() {
		super();
	}

	public static String methodUnderTest() {
		return "test";
	}
	
	public void dejarReview(Usuario usuario, String mensaje, Double puntaje) {
	    LocalDateTime fecha = LocalDateTime.now();
	    Review review = new Review(fecha, usuario, this, mensaje, puntaje);
	    this.vendedor.setRating(puntaje);
	    
	    /*if (reviews == null) {
	        reviews = new ArrayList<>();
	    }*/
	    
	    reviews.add(review);
	}

}
