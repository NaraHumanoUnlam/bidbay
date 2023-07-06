package com.bidbay.service;
import java.util.List;

import com.bidbay.models.entity.Usuario;

import jakarta.servlet.http.HttpSession;

public interface IUsuarioService {


	public List <Usuario> findAll(); 
	public void save(Usuario usuario);
	public Usuario validarUsuario(String nick, String password);
	public Usuario findByUsername(String nick);
	public Usuario findByemail(String email);
	public String findNickById (Long object);
	public Usuario validarExistenciaUsuario(String nick, String email);
	
	public void puntuarVendedor(Long idUsuario, Double raiting);
	public double calcularPromedioRating(Long idUsuario);
	public Boolean chequearQueElUsuarioEsteLogeado(HttpSession session);
	public Usuario getUsuarioActualmenteLogeado(HttpSession session);
}
