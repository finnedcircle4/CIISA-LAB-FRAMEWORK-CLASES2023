package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IProductosDao;
import com.example.demo.dao.IProductosCategoriasDao;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductosCategorias;
import com.example.demo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
	private IProductosDao iProductosDao;
    
    @Autowired
	private IProductosCategoriasDao productosCategoriasDao;
    

    @Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) iProductosDao.findAll();
	}

	@Transactional
	public void save(Producto producto) {
		iProductosDao.save(producto);

	}

	@Transactional(readOnly = true)
	public Producto findOne(Long id) {
		// TODO Auto-generated method stub
		return iProductosDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iProductosDao.deleteById(id);
	}

	@Override
	public List<Producto> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductosCategorias> findProductoCatById(Long id) {
		// TODO Auto-generated method stub
		return productosCategoriasDao.findByProductoId(id);
	}


}