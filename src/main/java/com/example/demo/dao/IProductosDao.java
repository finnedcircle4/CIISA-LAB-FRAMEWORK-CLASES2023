package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Producto;

public interface IProductosDao extends CrudRepository<Producto, Long>{

}
