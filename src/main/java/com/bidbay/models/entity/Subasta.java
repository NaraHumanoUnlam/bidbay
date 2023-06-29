package com.bidbay.models.entity;

import java.util.HashMap;
import java.util.Map;


public class Subasta {
	private Long id;
	private HashMap<Usuario,Double> ofertantes;
	private Usuario subastador;
	private Producto producto;
	public Subasta() {
		super();
	}
	public Subasta(HashMap<Usuario, Double> ofertantes, Usuario subastador, Producto producto) {
		this.ofertantes = new HashMap<>();
		this.subastador = subastador;
		this.producto = producto;
	}
	public Subasta(Long id, HashMap<Usuario, Double> ofertantes, Usuario subastador, Producto producto) {
		this.id = id;
		this.ofertantes = ofertantes;
		this.subastador = subastador;
		this.producto = producto;
	}
	public void setId(long l) {
		this.id = l;
		
	}
	public HashMap<Usuario, Double> getOfertantes() {
		return ofertantes;
	}
	public Usuario getSubastador() {
		return subastador;
	}
	public void setSubastador(Usuario subastador) {
		this.subastador = subastador;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Long getId() {
		return id;
	}

	public void agregarOfertante(Usuario ofertante, Double oferta) {
	
	 this.ofertantes.put(ofertante, oferta);
		
	}
	public Usuario obtenerOfertantePorOferta(Double oferta) {
		
		  for (Map.Entry<Usuario, Double> entry : ofertantes.entrySet()) {
	            if (entry.getValue() == oferta) {
	                return entry.getKey();
	            }
	        }
	        return null;
	}

	
}
