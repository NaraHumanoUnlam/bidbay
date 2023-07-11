package com.bidbay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bidbay.models.dao.IModalidadDao;
import com.bidbay.models.entity.Modalidad;

@Service
public class ModalidadServiceImpl implements IModalidadService{

	   @Autowired
	    private IModalidadDao modalidadDao;
		
		@Override
		@Transactional(readOnly = true)
		public List<Modalidad> findAll() {
			 return (List<Modalidad>)modalidadDao.findAll();
		}

		@Override
		@Transactional
		public void save(Modalidad c) {
			modalidadDao.save(c);
		}

		@Override
		@Transactional(readOnly = true)
		public Modalidad findOne(Long id) {
			return modalidadDao.findById(id).orElse(null);
		}

		@Override
		@Transactional
		public void delete(Long id) {
			modalidadDao.deleteById(id);
			
		}

}
