package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
		private IUserDao IUserDao;
	    

	    @Transactional(readOnly = true)
		public List<Users> findAll() {
			return (List<Users>) IUserDao.findAll();
		}

		@Transactional
		public void save(Users categoria) {
			IUserDao.save(categoria);

		}

		@Transactional(readOnly = true)
		public Users findOne(Long id) {
			// TODO Auto-generated method stub
			return IUserDao.findById(id).orElse(null);
		}

		@Transactional
		public void delete(Long id) {
			IUserDao.deleteById(id);
		}

		@Override
		public List<Users> findByNombre(String term) {
			// TODO Auto-generated method stub
			return null;
		}

}
