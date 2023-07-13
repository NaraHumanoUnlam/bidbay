package com.bidbay.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ISubastaDao;
import com.bidbay.models.entity.Ofertante;
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
	public void crearSubasta(Long id,Double precio_inicial,LocalDateTime fecha, Usuario usuario) {
		subastaDao.crearSubasta(id,precio_inicial, fecha, usuario,precio_inicial);
		
	}

	@Override
	public void agregarProducto(Long idProducto, Long id) {
		subastaDao.agregarProductoSubasta(idProducto, id);
	}

	@Override
	public void save(Subasta subasta) {
		subastaDao.save(subasta);
		
	}

	@Override
	public Subasta findById(@Valid Long idSubasta) {
		return subastaDao.findById(idSubasta).orElse(null);
	}

	@Override
	public void agregarOfertante(Ofertante ofertante, Long idsubasta) {
		subastaDao.agregarOfertante(ofertante.getOferta(),idsubasta,ofertante.getUsuario().getId());		
	}

	@Override
	public Subasta buscarPorProducto(Long idProd) {
		// TODO Auto-generated method stub
		return subastaDao.buscarPorProducto(idProd);
	}
}
