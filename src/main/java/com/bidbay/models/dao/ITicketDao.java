package com.bidbay.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long>{
	@Query(value = "select * from ticket  where id_user=?1", nativeQuery = true)
	public List<Ticket> detallesTicket(Long id_usuario);
	
	@Query(value = "select c.id from compras c join pago p on c.id_pago = p.id_pago where p.ticket =?", nativeQuery = true)
	public List<Long> detallesProductosxTicket(Long id_ticket);
	
	
}


