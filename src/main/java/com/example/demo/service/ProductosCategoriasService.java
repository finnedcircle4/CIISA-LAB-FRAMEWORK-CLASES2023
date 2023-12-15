package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductosCategorias;

@Service
public interface ProductosCategoriasService {
    public List<ProductosCategorias> findAll();
    
    public List<ProductosCategorias> findByProductoId(Long id);

	public void save(ProductosCategorias productosCategorias);

	public ProductosCategorias findOne(Long id);

	public void delete(Long id);

	public List<ProductosCategorias> findByNombre(String term);

	
}
