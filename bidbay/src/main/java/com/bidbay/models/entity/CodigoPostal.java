package com.bidbay.models.entity;

public class CodigoPostal {
	private Integer id;
	private String localidad;
	private String provincia;
	
	
	public CodigoPostal(Integer id, String localidad, String provincia) {
		this.id = id;
		this.localidad = localidad;
		this.provincia = provincia;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
}
