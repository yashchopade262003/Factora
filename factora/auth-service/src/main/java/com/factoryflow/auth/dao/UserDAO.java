package com.factoryflow.auth.dao;

import java.util.Optional;

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
	
	
	public Optional<User> userLogin(String email) {
		
		  return  repository.findByEmail(email);
	}

}
