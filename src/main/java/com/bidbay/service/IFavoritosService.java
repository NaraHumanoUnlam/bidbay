package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Favoritos;

public interface IFavoritosService {
	public List <Favoritos> findAll();
	public void delete (Long id);
	public void save (Favoritos favoritos);
}
