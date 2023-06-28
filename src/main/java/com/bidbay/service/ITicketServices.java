package com.bidbay.service;
import java.util.List;
import com.bidbay.models.entity.Ticket;

public interface ITicketServices {

	public List<Ticket> findAll();

	public void save(Ticket ticket);

	public Ticket findOne(Long id);

	public void delete(Long id);
	
}
