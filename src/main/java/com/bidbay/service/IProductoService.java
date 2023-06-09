package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public void save(Producto producto);

	public Producto findOne(Long id);

	public void delete(Long id);

	public List<Producto> findByName(String name);

	public List<Producto> orderList(String orden);

	public List<Producto> findByCategoriaId(Long id);

}
