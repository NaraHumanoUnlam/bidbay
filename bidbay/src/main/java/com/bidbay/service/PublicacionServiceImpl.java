package com.bidbay.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import com.bidbay.models.dao.IPublicacionDao;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Publicacion;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicacionServiceImpl implements IPublicacionService{
	
    @Autowired
    private IPublicacionDao publicacionDao;
	
    @Override
    @Transactional(readOnly = true)
	public List<Publicacion> findAll() {
    	return (List<Publicacion>) publicacionDao.findAll();
	}

	@Override
	@Transactional
	public void save(Publicacion publicacion) {
		publicacionDao.save(publicacion);
	}

    @Override
    @Transactional(readOnly = true)
	public Publicacion findOne(Long id) {
    	return publicacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		publicacionDao.deleteById(id);
	}

    @Override
    @Transactional(readOnly = true)
	public List<Publicacion> findByProductoName(String name) {
	    List<Publicacion> publicacionesEncontradas = new ArrayList<>();
	    for (Publicacion pub : findAll()) {
	        Producto pro = pub.getProducto();
	        if (pro.getNombre().equals(name)) {
	            publicacionesEncontradas.add(pub);
	        }
	    }
	    return publicacionesEncontradas;
	}
	
    @Override
    @Transactional(readOnly = true)
	public Publicacion findByProductoId(Long id) {
	    for (Publicacion pub : findAll()) {
	        Producto pro = pub.getProducto();
	        if (pro.getId().equals(id)) {
	            return pub;
	        }
	    }
	    return null;
	}

    @Override
    @Transactional(readOnly = true)
	public List<Publicacion> ordenarPorPrecio(String orden) {
	    List<Publicacion> listaOrdenada = findAll();
	    if (orden.equalsIgnoreCase("asc")) {
	        Collections.sort(listaOrdenada);
	    } else if (orden.equalsIgnoreCase("desc")) {
	        Collections.sort(listaOrdenada, Collections.reverseOrder());
	    }
	    
	    return listaOrdenada;
	}

    @Override
    @Transactional(readOnly = true)
	public List<Publicacion> ordenarPorFecha(Date desde, Date hasta) {
		return null;
	}

}
