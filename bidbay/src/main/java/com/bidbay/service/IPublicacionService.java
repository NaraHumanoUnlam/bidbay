package com.bidbay.service;
import java.util.Date;
import java.util.List;
import com.bidbay.models.entity.Publicacion;

public interface IPublicacionService {

    public List <Publicacion> findAll(); 
    public void save (Publicacion publicacion); 
    public Publicacion findOne(Long id); 
    public void delete(Long id);
    public List <Publicacion> findByProductoName(String name);
    public Publicacion findByProductoId(Long id);

    public List <Publicacion> ordenarPorPrecio(String orden); 
    public List <Publicacion> ordenarPorFecha(Date desde, Date hasta); 
	
	
}
