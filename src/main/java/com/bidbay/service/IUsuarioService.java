package com.bidbay.service;
import java.util.List;

import com.bidbay.models.entity.Usuario;

import jakarta.servlet.http.HttpSession;

public interface IUsuarioService {


	public List <Usuario> findAll(); 
	public Usuario findById(Long idUsuario);
	public void save(Usuario usuario);
	public void saveEdit(Usuario usuario);
	public Usuario validarUsuario(String nick, String password);
	public Usuario findByUsername(String nick);
	public Usuario findByemail(String email);
	public String findNickById (Long object);
	public Usuario validarExistenciaUsuario(String nick, String email);
	
	public Boolean chequearQueElUsuarioEsteLogeado(HttpSession session);
	public Usuario getUsuarioActualmenteLogeado(HttpSession session);
}
