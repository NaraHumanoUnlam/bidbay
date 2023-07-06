package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.OperacionCV;

public interface IOperacionCV extends CrudRepository<OperacionCV, Long> {
    @Query(value = "select * from operacioncv WHERE usuario_vende =?1", nativeQuery = true)
    public List<OperacionCV> detalleVentas(Long idUsuario);
    
    @Query(value = "select * from operacioncv WHERE usuario_compra = ?1", nativeQuery = true)
    public List<OperacionCV> detalleCompras(Long idUsuario);
}
