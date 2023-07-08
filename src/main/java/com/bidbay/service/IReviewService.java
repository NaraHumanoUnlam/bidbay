package com.bidbay.service;

import jakarta.servlet.http.HttpSession;

public interface IReviewService {
	
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId);

}
