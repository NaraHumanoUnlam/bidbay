package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Producto;

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{

}
