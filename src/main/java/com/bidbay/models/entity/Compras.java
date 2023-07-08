package com.bidbay.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "compras")
public class Compras  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Long IdUsuario;
	
	private Long IdPago;
	
	private Double monto;
	
	private Date fecha;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<DetalleCompras> detalles;
	

	public Compras() {
		this.detalles=new ArrayList<DetalleCompras>();
	}
	
	public Compras(Long idUsuario) {
		this.detalles=new ArrayList<DetalleCompras>();
		this.IdUsuario = idUsuario;
	}
    
	public List<DetalleCompras> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCompras> detalles) {
		this.detalles = detalles;
		Double precioTotal = 0.0;
		for(DetalleCompras detalle : detalles) {
            Integer stock = detalle.getCantidad();
            Double precio = detalle.getPrecioUnitario();
            precioTotal = stock * precio;
        }
		this.monto = precioTotal;
	}
	
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		IdUsuario = idUsuario;
	}
	public Long getIdPago() {
		return IdPago;
	}
	public void setIdPago(Long idPago) {
		IdPago = idPago;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}