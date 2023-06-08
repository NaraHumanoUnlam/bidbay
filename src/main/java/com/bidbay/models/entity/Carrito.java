package com.bidbay.models.entity;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carrito")
public class Carrito implements Serializable {
    // ...
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private Long idUsuario;
    
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoItem> carritoItems;
    
    public Carrito() {}
    
    public Carrito(Long idUsuario) {
		// TODO Auto-generated constructor stub
    	this.idUsuario = idUsuario;
    	this.carritoItems = new ArrayList<>();
	}

	public List<CarritoItem> getCarritoItems() {
        return carritoItems;
    }

    public void setCarritoItems(List<CarritoItem> carritoItems) {
        this.carritoItems = carritoItems;
    }

    public void addCarritoItem(CarritoItem carritoItem) {
        carritoItems.add(carritoItem);
        carritoItem.setCarrito(this);
    }

    public void removeCarritoItem(CarritoItem carritoItem) {
        carritoItems.remove(carritoItem);
        carritoItem.setCarrito(null);
    }

	public Long getId() {
		return id;
	}

	public Object getIdUsuario() {
		// TODO Auto-generated method stub
		return this.idUsuario;
	}
}
