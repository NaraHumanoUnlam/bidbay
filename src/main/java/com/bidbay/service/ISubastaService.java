package com.bidbay.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bidbay.models.entity.Ofertante;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;

import jakarta.validation.Valid;

public interface ISubastaService {
	
	public Subasta obtenerSubasta(Long id);

	public List<Subasta> findAll();

	public void eliminarUna(Long id);

	void crearSubasta(Long id,Double precio_inicial, LocalDateTime fechaSQLString, Usuario usuario);

	public void agregarProducto(Long producto, Long id);

	public void save(@Valid Subasta subasta);

	public Subasta findById(@Valid Long idSubasta);

	public void agregarOfertante(Ofertante ofertante, Long idSubasta);

}
