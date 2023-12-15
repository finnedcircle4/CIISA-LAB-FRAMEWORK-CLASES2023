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
import com.example.demo.service.ProductosCategoriasService;

@Service
public class ProductosCategoriasServiceImpl implements ProductosCategoriasService{

    @Autowired
	private IProductosCategoriasDao productosCategoriasDao;

    @Transactional(readOnly = true)
	public List<ProductosCategorias> findAll() {
		return (List<ProductosCategorias>) productosCategoriasDao.findAll();
	}

	@Transactional
	public void save(ProductosCategorias productosCategorias) {
		productosCategoriasDao.save(productosCategorias);

	}

	@Transactional(readOnly = true)
	public ProductosCategorias findOne(Long id) {
		// TODO Auto-generated method stub
		return productosCategoriasDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		productosCategoriasDao.deleteById(id);
	}

	@Override
	public List<ProductosCategorias> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProductosCategorias> findByProductoId(Long id) {
		// TODO Auto-generated method stub
		return productosCategoriasDao.findByProductoId(id);
	}

}