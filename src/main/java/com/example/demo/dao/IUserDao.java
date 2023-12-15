package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Users;

public interface IUserDao extends CrudRepository<Users, Long> {

}
