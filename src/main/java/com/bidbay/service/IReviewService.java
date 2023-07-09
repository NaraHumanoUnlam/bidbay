package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Review;

public interface IReviewService {
	public List<Review> findAll();
	
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId, Long notificacionId);

	public boolean usuarioHabilitado(Long id, Long idProducto, Long notificacionId);

}
