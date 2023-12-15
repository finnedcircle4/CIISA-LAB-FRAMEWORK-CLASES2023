package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;


public interface UserService {
	public List<Users> findAll();
	public void save(Users user);
	public Users findOne(Long id);
	public void delete(Long id);
	public List<Users> findByNombre(String term);
}
