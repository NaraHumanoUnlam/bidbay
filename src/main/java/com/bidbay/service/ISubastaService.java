package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Subasta;

public interface ISubastaService {
	
	public Subasta obtenerSubasta(Long id);

	public List<Subasta> findAll();

}
