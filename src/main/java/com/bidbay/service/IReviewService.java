package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Review;

import jakarta.servlet.http.HttpSession;

public interface IReviewService {
	
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId, Long notificacionId);

	public boolean usuarioHabilitado(Long id, Long idProducto, Long notificacionId);

	public List<Review> getReviewsPorProducto(Long id);

}
