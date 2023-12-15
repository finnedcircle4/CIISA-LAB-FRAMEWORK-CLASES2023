package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.ProductosCategorias;

public interface IProductosCategoriasDao extends CrudRepository<ProductosCategorias, Long>{

	List<ProductosCategorias> findByCategoriaId(Long id);
	List<ProductosCategorias> findByProductoId(Long id);

}

