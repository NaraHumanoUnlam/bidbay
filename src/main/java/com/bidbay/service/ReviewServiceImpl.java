package com.bidbay.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
	@Transactional(readOnly = true)
	public List<Review> findAll() {
		   return (List<Review>)reviewDao.findAll();
	}

	
	@Override
	@Transactional
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, Long usuarioId, Long notificacionId) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.findById(usuarioId).orElse(null);
        Producto producto = productoDao.findById(idProducto).orElse(null);
        LocalDate currentDate = LocalDate.now();
        Date fecha = java.sql.Date.valueOf(currentDate);
        Boolean usuarioHabilitado = false;
        if (this.chequearQueElUsuarioYProductoSeanValidos(usuario, producto)) {
        	usuarioHabilitado = this.chequearSiElUsuarioEstaHabilitado(usuarioId, idProducto, notificacionId);
        }
        
        if(usuarioHabilitado) {
        	Review reviewNueva = new Review(fecha, usuario, producto, mensaje, puntaje);
        	producto.agregarReview(reviewNueva);
        	reviewDao.save(reviewNueva);
        	usuarioDao.actualizarRating(usuarioId);
        	productoDao.save(producto);
        	usuarioDao.save(usuario);
        	if(notificacionId != 0) {
        		this.notificacionDao.eliminarNotificacion(notificacionId);
        	}
        } 
      }


	private boolean chequearQueElUsuarioYProductoSeanValidos(Usuario usuario, Producto producto) {
		// TODO Auto-generated method stub
		 if (usuario != null && producto != null) {
			 return true;
		 }
		 return false;
	}


	private boolean chequearSiElUsuarioEstaHabilitado(Long usuarioId, Long idProducto, Long notificacionId) {
		// TODO Auto-generated method stub
		boolean resultado = this.usuarioHabilitado(usuarioId, idProducto);
		if(resultado == false && notificacionId != 0) {
			this.notificacionDao.eliminarNotificacion(notificacionId);
		}
		return resultado;
	}


	@Override
	public boolean usuarioHabilitado(Long id, Long idProducto) {
		// TODO Auto-generated method stub
		if(reviewDao.usuarioComproProducto(id, idProducto) >= 1 && reviewDao.usuarioNoDejoReview(id, idProducto) == 0) {
			return true;
		}
		return false;
	}


	@Override
	public List<Review> getReviewsPorProducto(Long id) {
		// TODO Auto-generated method stub
		return reviewDao.filtrarReviewsPorProducto(id);
	}


	@Override
	public List<Review> getReviewsPorUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		return reviewDao.filtrarReviewsPorUsuario(idUsuario);
	}


	@Override
	public void borrarReview(Long id) {
		// TODO Auto-generated method stub
		this.reviewDao.deleteById(id);
	}

}
