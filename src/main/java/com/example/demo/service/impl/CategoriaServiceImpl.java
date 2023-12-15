package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ICategoriaDao;
import com.example.demo.entity.Categoria;
import com.example.demo.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	 @Autowired
		private ICategoriaDao ICategoriaDao;
	    

	    @Transactional(readOnly = true)
		public List<Categoria> findAll() {
			return (List<Categoria>) ICategoriaDao.findAll();
		}

		@Transactional
		public void save(Categoria categoria) {
			ICategoriaDao.save(categoria);

		}

		@Transactional(readOnly = true)
		public Categoria findOne(Long id) {
			// TODO Auto-generated method stub
			return ICategoriaDao.findById(id).orElse(null);
		}

		@Transactional
		public void delete(Long id) {
			ICategoriaDao.deleteById(id);
		}

		@Override
		public List<Categoria> findByNombre(String term) {
			// TODO Auto-generated method stub
			return null;
		}

}
