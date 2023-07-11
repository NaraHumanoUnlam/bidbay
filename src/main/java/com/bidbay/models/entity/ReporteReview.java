package com.bidbay.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reporte_review")
public class ReporteReview {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "idReview")
    private Review review;
    
    public ReporteReview(Review reviewDenunciada, Usuario denunciante) {
		// TODO Auto-generated constructor stub
    	this.review = reviewDenunciada;
    	this.usuario = denunciante;
	}
    
    public ReporteReview() {
		// TODO Auto-generated constructor stub
	}


    
}
