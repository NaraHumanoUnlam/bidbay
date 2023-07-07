package com.bidbay.service;

import java.util.List;

import com.bidbay.models.entity.Categoria;
import com.bidbay.models.entity.OperacionCV;
import com.bidbay.models.entity.Producto;

import jakarta.servlet.http.HttpSession;

public interface IProductoService {

	public List<Producto> findAll();
	public void save(Producto producto);
	public Producto findOne(Long id);
	public void delete(Long id);
	public List<Producto> findByName(String name);
	public List<Producto> orderList(String orden);
	public List<Producto> findByCategoriaId(Long id);
	public List<Producto> productoDelUsuario(Long id_usuario);
	public List<Producto> orderListDelUsuario(String orden, Long id_usuario);
	public List<Producto> findByNameDelUsuario(String name, Long id_usuario);
	public Integer comprasDelUsuario(Long id_usuario);
	public Integer ventasDelUsuario(Long id_usuario);
	public List<OperacionCV> detalleComprasDelUsuario(Long id_usuario);
	public List<OperacionCV> detalleVentasDelUsuario(Long id_usuario);
	public void dejarReview(Long idProducto, String mensaje, Double puntaje, HttpSession session);
}
