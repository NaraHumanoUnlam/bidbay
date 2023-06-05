package com.bidbay.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bidbay.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
}
