package com.factoryflow.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factoryflow.auth.entity.User;
import com.factoryflow.auth.repository.UserRepository;

@Repository
public class UserDAO {
	@Autowired
	private UserRepository repository;
	
	public User registerUser(User entity) {
		User save = repository.save(entity);
		return save;
	}
	

}
