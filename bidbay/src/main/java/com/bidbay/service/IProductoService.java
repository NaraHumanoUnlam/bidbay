package com.bidbay.service;

import java.util.List;
import com.bidbay.models.entity.Producto;

public interface IProductoService {

	  public List <Producto> findAll(); 
	  public void save(Producto producto);
	  public Producto findOne(Long id);
      public void delete(Long id);

}
