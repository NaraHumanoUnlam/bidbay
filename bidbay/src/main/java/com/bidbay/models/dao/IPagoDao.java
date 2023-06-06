package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Pago;

	public interface IPagoDao extends CrudRepository<Pago, Long> {
}