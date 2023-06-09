package com.bidbay.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.entity.Pago;
import com.bidbay.models.entity.Producto;
import com.bidbay.models.entity.Usuario;

import jakarta.servlet.http.HttpSession;

import com.bidbay.excepciones.ArchivoException;
import com.bidbay.models.dao.INotificacionDao;
import com.bidbay.models.dao.IProductoDao;
import com.bidbay.models.dao.IUsuarioDao;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private INotificacionDao notificacionDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	public Usuario findById(Long idUsuario) {

		return usuarioDao.findById(idUsuario).orElse(null);
	}
	
	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		String mensaje = "¡Bienvenido a Bidbay " + usuario.getNick() + "!";
		Long idUser = usuario.getId() == null ? 0 : usuario.getId();
		try {

			notificacionDao.crearNotificacion("Bienvenida", mensaje,idUser, "");
		 } catch (Exception e) {
		      e.printStackTrace();
		}
	}
	
	public void saveEdit(Usuario usuario) {
		usuarioDao.save(usuario);
	}
	
	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	public Usuario validarUsuario(String nick, String password) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		// agregar usuarios a la lista
		for (Usuario usuario : usuarios) {
		    if (usuario.getNick().equals(nick) && usuario.getPassword().equals(password))
		    	return usuario;
		}
		return null;
	}
	
	public Usuario validarExistenciaUsuario(String nick, String email) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		// agregar usuarios a la lista
		for (Usuario usuario : usuarios) {
		    if (usuario.getNick().equals(nick) || usuario.getEmail().equals(email))
		    	return usuario;
		}
		return null;
	}	

	public Usuario findByUsername(String nick) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		for (Usuario usuario : usuarios) {
		    if (usuario.getNick().equals(nick))
		    	return usuario;
		}
		return null;
	}
	
	public Usuario findByemail(String email) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		for (Usuario usuario : usuarios) {
		    if (usuario.getEmail().equals(email))
		    	return usuario;
		}
		return null;
	}
	
	
	public String findNickById (Long id) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		for (Usuario usuario : usuarios) {
		    if (usuario.getId().equals(id))
		    	return usuario.getNick();
		}
		return null;
	}

	
	@Override
    public Boolean chequearQueElUsuarioEsteLogeado(HttpSession session) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
        if (idUsuario == null) {
            return false;
        }
        return true;
    }
    
	@Override
    public Usuario getUsuarioActualmenteLogeado(HttpSession session) {
    	Long idUsuario = (Long) session.getAttribute("idUsuario");
    	return findOne(idUsuario);
    }
	
	/* Relacionados a Producto */
    
    public void agregarProductoAFavoritos(Producto p, HttpSession session) {
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	if(!getListaDeProductosFavoritos(session).contains(p)) {
        	//u.getFavoritos().add(p);
    	}
    }
    
    public void eliminarProductoDeFavoritos(Producto p, HttpSession session) {
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	if(getListaDeProductosFavoritos(session).contains(p)) {
    	  	//u.getFavoritos().remove(p);
    	}
    }
        
    public List<Producto> getListaDeProductosFavoritos(HttpSession session){
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	//return u.getFavoritos();
    	return null;
    }
    
    public void agregarProductoAPublicaciones(Producto p, HttpSession session) {
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	if(!getListaDeProductosPublicaciones(session).contains(p)) {
        	u.getPublicaciones().add(p);
    	}
    }
    
    public void eliminarProductoDePublicaciones(Producto p, HttpSession session) {
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	if(getListaDeProductosPublicaciones(session).contains(p)) {
    	  	u.getPublicaciones().remove(p);
    	}
    }
        
    public List<Producto> getListaDeProductosPublicaciones(HttpSession session){
    	Long idUsuario = getUsuarioActualmenteLogeado(session).getId();
    	Usuario u = findOne(idUsuario);
    	return u.getPublicaciones();
    }

    
	
}
