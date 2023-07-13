package com.bidbay.models.dao;



import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Ofertante;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;

import jakarta.transaction.Transactional;

public interface ISubastaDao extends CrudRepository<Subasta, Long>{
	@Query(value = "select * from subastas where id=?", nativeQuery = true)
	public Subasta obtenerSubasta(Long id);
	
	@Modifying
	@Transactional
	@Query(value = "insert into subastas (id,precio_inicial,fecha_limite,subastador,precio_maximo) VALUES (?,?,?,?,?)", nativeQuery = true)
	public void crearSubasta(Long id,Double precioInicial, LocalDateTime fecha, Usuario subastador, Double precio_maximo);
	

	@Query(value = "update subastas set producto = ? where id=?", nativeQuery = true)
	public void agregarProductoSubasta(Long producto, Long id);

	
	@Modifying
	@Transactional
	@Query(value = "insert into ofertante (oferta,subasta_id,usuarios) VALUES (?,?,?)", nativeQuery = true)
	public void agregarOfertante(Double bigDecimal, Long subasta, Long idUsuario);

	@Modifying
	@Query(value = "delete from ofertante where subasta_id = ? and id <> ?", nativeQuery = true)
	public void eliminarOFertantes(Long idSubasta, Long id);

	@Query(value = "SELECT o.* FROM ofertante o WHERE subasta_id = ?1 AND oferta >= ?2", nativeQuery = true)
	public Ofertante obtenerOfertante(Long idSubasta, Double mayor);
	
	
}
