package com.bidbay.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		   return (List<Producto>)productoDao.findAll();
	}

    @Override
    @Transactional
    public void save(Producto producto) {
    	productoDao.save(producto);
    }

	@Override
	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	
    @Override
    @Transactional
    public void delete(Long id) {
    	productoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByName(String name) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : findAll()) {
            if (p.getNombre().toLowerCase().contains(name.toLowerCase())) {
            	productosEncontrados.add(p);
            }
        }
        return productosEncontrados;
    }

	@Override
	public List<Producto> findByPrecio(Integer minimo, Integer maximo) {
		// se castea a double
		return null;
	}

	@Override
	public List<Producto> orderList(String orden) {
		List<Producto> listaOrdenada;
        listaOrdenada = findAll();
        if (orden.equalsIgnoreCase("asc")) {
            Collections.sort(listaOrdenada);
        } else if (orden.equalsIgnoreCase("desc")) {
            Collections.sort(listaOrdenada, Collections.reverseOrder());
        }
        return listaOrdenada;
	}

	@Override
	public List<Producto> findByCategoria(String categia) {
		// TODO Auto-generated method stub
		return null;
	}


	

    


	
	
	
	
}
