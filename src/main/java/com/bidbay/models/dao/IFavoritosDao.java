package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Favoritos;

public interface IFavoritosDao extends CrudRepository<Favoritos, Long>{
	@Query(value = "select * from favoritos where usuario_id=?1", nativeQuery = true)
	public List<Favoritos> favoritosDelusuario(Long id_usuario);

}
