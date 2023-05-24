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

	public Usuario findByUsername(String nick) {
		List<Usuario> usuarios = (List<Usuario>) usuarioDao.findAll();
		for (Usuario usuario : usuarios) {
		    if (usuario.getNick().equals(nick))
		    	return usuario;
		}
		return null;
	}


}
