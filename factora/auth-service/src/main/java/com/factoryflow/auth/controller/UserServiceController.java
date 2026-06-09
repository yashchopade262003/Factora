package com.factoryflow.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoryflow.auth.InterfaceService.IUserService;
import com.factoryflow.auth.dto.UserDTO;


@RestController
@RequestMapping("/user")
public class UserServiceController {
	@Autowired
	private IUserService iUserService;
	
	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody UserDTO dto) {
		UserDTO registerUser = iUserService.registerUser(dto);
		return registerUser;
	}
	
	
	
	
	
}
