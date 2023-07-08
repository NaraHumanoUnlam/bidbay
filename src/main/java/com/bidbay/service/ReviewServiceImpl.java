package com.bidbay.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.IReviewDao;
import com.bidbay.models.dao.IUsuarioDao;
import com.bidbay.models.entity.Notificacion;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;
import com.bidbay.models.entity.Review;

import jakarta.servlet.http.HttpSession;

@Service
public class ReviewServiceImpl implements IReviewService{
	
	@Autowired
	private IReviewDao reviewDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private INotificacionDao notificacionDao;
	
	
	@Override
	@Transactional
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.findById(usuarioId).orElse(null);
        Producto producto = productoDao.findById(idProducto).orElse(null);
        LocalDate currentDate = LocalDate.now();
        Date fecha = java.sql.Date.valueOf(currentDate);
        
        if (usuario != null && producto != null) {
        	Review reviewNueva = new Review(fecha, usuario, producto, mensaje, puntaje);
        	producto.agregarReview(reviewNueva);
        	reviewDao.save(reviewNueva);
        	usuarioDao.actualizarRating(usuarioId);
        	productoDao.save(producto);
        	this.notificacionDao.crearNotificacion("Review", "Generaste una review", usuarioId,"");
            usuarioDao.save(usuario);
            } 
        }

}
