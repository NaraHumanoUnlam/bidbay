package com.bidbay.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;

import jakarta.validation.Valid;

public interface ISubastaService {
	
	public Subasta obtenerSubasta(Long id);

	public List<Subasta> findAll();

	public void eliminarUna(Long id);

	void crearSubasta(Double precio_inicial, Date fecha, Time hora, Usuario usuario);

	public void agregarProducto(@Valid Producto producto, Long id);

}
