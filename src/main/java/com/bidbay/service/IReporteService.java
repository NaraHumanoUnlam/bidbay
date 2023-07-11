package com.bidbay.service;

import java.util.List;
import java.util.Map;

import com.bidbay.models.entity.Review;

public interface IReporteService {

	public void reportarProducto(Long idProducto, Long id);

	public void reportarReview(Long idReview, Long id);

	public void borrarReporteReview(Long id);

	public void borrarReporteProducto(Long id);






}
