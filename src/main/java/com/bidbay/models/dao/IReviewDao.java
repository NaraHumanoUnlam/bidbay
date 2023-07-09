package com.bidbay.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Review;
import com.bidbay.models.entity.Subasta;

public interface IReviewDao extends CrudRepository<Review, Long>{
	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM compras c JOIN detalle_compras dc ON c.id = dc.compra_id JOIN productos p ON dc.productos = p.id WHERE c.id_usuario = ? AND p.id = ? AND c.id_pago IS NOT NULL", nativeQuery = true)
	public int usuarioComproProducto(Long idUsuario, Long idProducto);
	
	
	@Query(value = "SELECT * from review r where r.producto_id = ?", nativeQuery = true)
	public List<Review> filtrarReviewsPorProducto(Long idProducto);
	
	@Query(value = "SELECT COUNT(*) FROM review r where r.usuario_id = ? AND r.producto_id = ?", nativeQuery = true)
	public int usuarioNoDejoReview(Long id, Long idProducto);

	@Query(value = "SELECT * from review r where r.usuario_id = ?", nativeQuery = true)
	public List<Review> filtrarReviewsPorUsuario(Long idUsuario);
	
}
