package com.bidbay.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ISubastaDao;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;

import jakarta.validation.Valid;

@Repository
@Service
public class SubastaService implements ISubastaService{

	@Autowired
	private ISubastaDao subastaDao;

	@Override
	public Subasta obtenerSubasta(Long id) {
		return subastaDao.obtenerSubasta(id);
	}

	@Override
	public List<Subasta> findAll() {
		return (List<Subasta>) subastaDao.findAll();
	}

	@Override
	public void eliminarUna(Long id) {
		subastaDao.deleteById(id);
	}

	@Override
	public void crearSubasta(Double precio_inicial,Date fecha,Time hora, Usuario usuario) {
		subastaDao.crearSubasta(precio_inicial, fecha, hora, usuario,precio_inicial);
		
	}

	@Override
	public void agregarProducto(@Valid Producto producto, Long id) {
		subastaDao.agregarProductoSubasta(producto, id);
		
	}
}
