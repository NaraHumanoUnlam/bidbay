package com.bidbay.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Ticket;


public interface ITicketDao extends CrudRepository<Ticket, Long>{
}

