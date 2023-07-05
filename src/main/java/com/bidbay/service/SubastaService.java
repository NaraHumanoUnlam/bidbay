package com.bidbay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bidbay.models.dao.ISubastaDao;
import com.bidbay.models.entity.Subasta;

@Repository
@Service
public class SubastaService implements ISubastaService{

	@Autowired
	private ISubastaDao subastaDao;

	@Override
	public Subasta obtenerSubasta(Long id) {
		return subastaDao.obtenerSubasta(id);
	}
}
