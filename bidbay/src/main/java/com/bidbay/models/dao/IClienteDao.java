package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Cliente;

	public interface IClienteDao extends CrudRepository<Cliente, Long> {
}