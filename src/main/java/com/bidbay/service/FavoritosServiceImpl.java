package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bidbay.models.dao.IFavoritosDao;
import com.bidbay.models.entity.Favoritos;

public class FavoritosServiceImpl implements IFavoritosService{
	@Autowired
	private IFavoritosDao favoritosDao;
	
	@Override
	public List<Favoritos> findAll() {
		return   (List<Favoritos>) favoritosDao.findAll();
	}

	@Override
	public void delete(Long id) {
		 favoritosDao.deleteById(id);
	}

	@Override
	public void save(Favoritos favoritos) {
		favoritosDao.save(favoritos);	
	}
	
}