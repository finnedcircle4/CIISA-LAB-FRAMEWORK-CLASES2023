package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}

