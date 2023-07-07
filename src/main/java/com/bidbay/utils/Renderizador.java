package com.bidbay.utils;

import java.util.List;

import org.springframework.data.domain.Page;

public class Renderizador <T>{
	private String url;
	private Page<T> pagina;
	private Integer totalPaginas;
	private Integer numeroElementosPorPagina;
	private List<Elemento> paginas;
	private Integer paginaActual;
	
	public Renderizador(String url, Page<T> pagina,List<Elemento> paginas) {
		this.url = url;
		this.pagina = pagina;
		this.totalPaginas = this.pagina.getTotalPages();
		this.numeroElementosPorPagina = this.pagina.getSize();
		this.paginas = paginas;
		this.paginaActual = this.pagina.getNumber() +1;
	}
	
	

}
