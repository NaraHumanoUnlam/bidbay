package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Publicacion;

public interface IPublicacionDao extends CrudRepository<Publicacion, Long>{

}
