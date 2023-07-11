package com.bidbay.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.IReporteProductoDao;
import com.bidbay.models.dao.IReporteReviewDao;
import com.bidbay.models.dao.IReviewDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.models.entity.ReporteProducto;
import com.bidbay.models.entity.ReporteReview;
import com.bidbay.models.entity.Review;

@Service
public class ReporteServiceImpl implements IReporteService{
	
	@Autowired
	private IReviewDao reviewDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IReporteProductoDao reporteProductoDao;
	
	@Autowired
	private IReporteReviewDao reporteReviewDao;
	
	@Autowired
	private INotificacionDao notificacionDao;

	@Override
	public void reportarProducto(Long idProducto, Long id) {
		// TODO Auto-generated method stub
		Producto productoDenunciado = this.productoDao.findById(idProducto).orElse(null);
		Usuario denunciante = this.usuarioDao.findById(id).orElse(null);
		if(productoDenunciado != null & denunciante != null) {
			ReporteProducto reporte = new ReporteProducto(productoDenunciado, denunciante);
			this.reporteProductoDao.save(reporte);
		}
	}

	@Override
	public void reportarReview(Long idReview, Long id) {
		// TODO Auto-generated method stub
		Review reviewDenunciada = this.reviewDao.findById(idReview).orElse(null);
		Usuario denunciante = this.usuarioDao.findById(id).orElse(null);
		if(reviewDenunciada != null & denunciante != null) {
			ReporteReview reporte = new ReporteReview(reviewDenunciada, denunciante);
			this.reporteReviewDao.save(reporte);
			this.mandarNotificacionReporte(id);
		}
	}
	
	@Transactional
	private void mandarNotificacionReporte(Long id) {
		// TODO Auto-generated method stub
		this.notificacionDao.crearNotificacion("Reporte", "Gracias por tu reporte, vamos a investigar la situación.", id, null);
	}
	
	@Transactional
	@Override
	public void borrarReporteReview(Long id) {
		// TODO Auto-generated method stub
		this.reporteReviewDao.borrarPorIdReview(id);
	}

	@Transactional
	@Override
	public void borrarReporteProducto(Long id) {
		// TODO Auto-generated method stub
		this.reporteProductoDao.borrarPorIdProducto(id);
	}

	

	

	

}
