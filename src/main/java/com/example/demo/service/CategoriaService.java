package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Categoria;


public interface CategoriaService {
	public List<Categoria> findAll();
	public void save(Categoria categoria);
	public Categoria findOne(Long id);
	public void delete(Long id);
	public List<Categoria> findByNombre(String term);
}

