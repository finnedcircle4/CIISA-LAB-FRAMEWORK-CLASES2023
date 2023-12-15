package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IEstadoDao;
import com.example.demo.entity.Estado;
import com.example.demo.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {
	 @Autowired
		private IEstadoDao IEstadoDao;
	    

	    @Transactional(readOnly = true)
		public List<Estado> findAll() {
			return (List<Estado>) IEstadoDao.findAll();
		}

		@Transactional
		public void save(Estado ingrediente) {
			IEstadoDao.save(ingrediente);

		}

		@Transactional(readOnly = true)
		public Estado findOne(Long id) {
			// TODO Auto-generated method stub
			return IEstadoDao.findById(id).orElse(null);
		}

		@Transactional
		public void delete(Long id) {
			IEstadoDao.deleteById(id);
		}

		@Override
		public List<Estado> findByNombre(String term) {
			// TODO Auto-generated method stub
			return null;
		}

}

