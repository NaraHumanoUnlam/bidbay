package com.bidbay.service;
import java.util.List;
import com.bidbay.models.entity.Usuario;

public interface IUsuarioService {


	public List <Usuario> findAll(); 
	public void save(Usuario usuario);
	//public Usuario validarLogin(String nick,String password);
}
