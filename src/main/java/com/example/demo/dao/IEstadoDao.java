package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Estado;

public interface IEstadoDao extends CrudRepository<Estado, Long> {

}
