package com.bidbay.models.entity;


import java.util.Iterator;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="subasta")
public class Subasta {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
//	//@ManyToMany(mappedBy = "subastas")
//	private List<Ofertante> ofertantes;
//	private Usuario subastador;
//	private Producto producto;
//	public Subasta() {
//		super();
//		this.ofertantes = new ArrayList<>();
//	}
//	public Subasta(Usuario subastador, Producto producto) {
//		this.subastador = subastador;
//		this.producto = producto;
//		this.ofertantes = new ArrayList<>();
//		
//	}
//	public Subasta(Long id, Usuario subastador, Producto producto) {
//		this.id = id;
//		this.subastador = subastador;
//		this.producto = producto;
//		this.ofertantes = new ArrayList<>();
//	}
//	public void setId(long l) {
//		this.id = l;
//		this.ofertantes = new ArrayList<>();
//		
//	}
//	
//	public Usuario getSubastador() {
//		return subastador;
//	}
//	public void setSubastador(Usuario subastador) {
//		this.subastador = subastador;
//	}
//	public Producto getProducto() {
//		return producto;
//	}
//	public void setProducto(Producto producto) {
//		this.producto = producto;
//	}
//	public Long getId() {
//		return id;
//	}
//	
//	  public List<Ofertante> getOfertantes() {
//	        return ofertantes;
//	    }
//
//	  public void agregarOfertante(Usuario user, Double valor) {
//		  Ofertante ofertantante = new Ofertante(user,valor);
//		  ofertantante.setId(user.getId());
//		  this.ofertantes.add(ofertantante);
//	  }
//	  
//	public Ofertante obtenerOfertantePorNombre(String nombre) {
//		
//			Ofertante nuevo = null;
//			for (int i = 0; i < ofertantes.size(); i++) {
//				if(ofertantes.get(i).getUsuario().getNombre().equals(nombre)) {
//					nuevo = ofertantes.get(i);
//				}
//				
//			}
//		return nuevo;
//	}
//	    
//	    

	

	
}
