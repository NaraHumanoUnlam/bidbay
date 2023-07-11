package com.bidbay.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reporte_producto")
public class ReporteProducto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;
    
    public ReporteProducto(Producto productoDenunciado, Usuario denunciante) {
		// TODO Auto-generated constructor stub
		this.producto = productoDenunciado;
		this.usuario = denunciante;
	}
    
    public ReporteProducto() {
		// TODO Auto-generated constructor stub
	}
}