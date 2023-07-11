package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Review;


import jakarta.servlet.http.HttpSession;


public interface IReviewService {
	public List<Review> findAll();
	
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId, Long notificacionId);

	public boolean usuarioHabilitado(Long id, Long idProducto);

	public List<Review> getReviewsPorProducto(Long id);

	public List<Review> getReviewsPorUsuario(Long idUsuario);

	public void borrarReview(Long id);

}
