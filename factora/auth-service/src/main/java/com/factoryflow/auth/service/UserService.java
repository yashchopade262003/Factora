package com.factoryflow.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factoryflow.auth.InterfaceService.IUserService;
import com.factoryflow.auth.dao.UserDAO;
import com.factoryflow.auth.dto.UserDTO;
import com.factoryflow.auth.entity.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserDAO userDAO;
	@Override
	public  UserDTO registerUser(UserDTO user) {
		 User userentity = mapper.map(user, User.class);
		 
		 User registerUser = userDAO.registerUser(userentity);
		 
		 UserDTO userdto = mapper.map(registerUser, UserDTO.class);
		 return userdto;
		
		
		
	}
}
