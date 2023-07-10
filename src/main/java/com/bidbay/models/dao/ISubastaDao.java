package com.bidbay.models.dao;


import java.sql.Date;
import java.sql.Time;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Subasta;
import com.bidbay.models.entity.Usuario;

import jakarta.transaction.Transactional;

public interface ISubastaDao extends CrudRepository<Subasta, Long>{
	@Query(value = "select * from subastas where id=?", nativeQuery = true)
	public Subasta obtenerSubasta(Long id);
	
	@Modifying
	@Transactional
	@Query(value = "insert into subastas (precio_inicial,fecha_limite,hora_limite,subastador,precio_maximo) VALUES (?,?,?,?,?)", nativeQuery = true)
	public Subasta crearSubasta(Double precioInicial, Date fecha_limite, Time hora_limite, Usuario subastador, Double precio_maximo);
	
	@Modifying
	@Transactional
	@Query(value = "update subastas set producto = ? where id=?", nativeQuery = true)
	public Subasta agregarProductoSubasta(Producto producto, Long id);
}
