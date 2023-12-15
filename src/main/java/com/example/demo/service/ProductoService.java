package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Producto;
import com.example.demo.entity.ProductosCategorias;

@Service
public interface ProductoService {
    public List<Producto> findAll();

	public void save(Producto producto);

	public Producto findOne(Long id);

	public void delete(Long id);

	public List<Producto> findByNombre(String term);
	
	public List<ProductosCategorias> findProductoCatById(Long id);
	

	
}

