package com.bidbay.service;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.IDetalleCompra;
import com.bidbay.models.dao.ITicketDao;
import com.bidbay.models.entity.Ticket;
import com.bidbay.models.entity.DetalleCompras;

import jakarta.transaction.Transactional;

@Service
public class TicketServicesImpl implements ITicketServices {

	@Autowired
	private ITicketDao ticketDao; 
	
	@Autowired
	private IDetalleCompra detalleCompraDao; 
	
	@Override
	@Transactional
	public void save(Ticket ticket) {
		ticketDao.save(ticket);
	}

	@Override
	@Transactional//(readOnly = true)
	public Ticket findOne(Long id) {
		return ticketDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ticketDao.deleteById(id);
	}

	@Override
	@Transactional//(readOnly = true)
	public List<Ticket> findAll() {
		return (List<Ticket>)ticketDao.findAll();
	}
	
	@Override
	public List<Ticket> detallesTicketPorUsuario(Long id_usuario) {
		return ticketDao.detallesTicket(id_usuario);
	}
	
	@Override
	public List<DetalleCompras> detallesProductosPorTicket(Long id_ticket) {
		List<Long> idsDetalles = ticketDao.detallesProductosxTicket(id_ticket);
	    List<DetalleCompras> detallesCompras = new ArrayList<>();
	    for (Long idDetalle : idsDetalles) {
	        detallesCompras.addAll(this.detalleCompraDao.detallesCompras(idDetalle));
	    }
	    return detallesCompras;
	}
	
	
	
}
