package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bidbay.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
