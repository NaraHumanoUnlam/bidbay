package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bidbay.models.entity.Usuario;
import com.bidbay.models.dao.IUsuarioDao;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
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
	public void puntuarVendedor(Long idUsuario, Double rating) {
	    Usuario vendedor = usuarioDao.findById(idUsuario).orElse(null);
	    if (vendedor != null) {
	        if (rating < 0) {
	            rating = 0.0;
	        } else if (rating > 5) {
	            rating = 5.0;
	        }
	        vendedor.setRating(rating);
	        usuarioDao.save(vendedor);
	    }
	}

	@Override
	public double calcularPromedioRating(Long idUsuario) {
		Usuario vendedor = usuarioDao.findById(idUsuario).orElse(null);
		if (vendedor != null) {
			return vendedor.getRating();
		}
		return 0.0;
	}
}
