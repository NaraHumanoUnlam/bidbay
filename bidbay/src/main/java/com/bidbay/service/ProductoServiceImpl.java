package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;

	@Override
	public List<Producto> findAll() {
		   return (List<Producto>)productoDao.findAll();
	}

    @Override
    @Transactional
    public void save(Producto producto) {
    	productoDao.save(producto);
    }

    


	
	
	
	
}
