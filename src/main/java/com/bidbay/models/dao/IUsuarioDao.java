package com.bidbay.models.dao;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bidbay.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	@Modifying
	@Query(value = "UPDATE Usuarios u SET u.rating = (SELECT AVG(r.puntaje) FROM Review r JOIN Productos p ON r.producto_id = p.id WHERE p.usuario_id = u.id) WHERE u.id = ?", nativeQuery = true)
	public void actualizarRating(Long usuarioId);
	
	
}
