package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Carrito;
import com.bidbay.models.entity.CarritoItem;

public interface ICarritoItemDao extends CrudRepository<CarritoItem, Long>{

}
