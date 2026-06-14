package com.factoryflow.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.factoryflow.auth.InterfaceService.IAuthService;
import com.factoryflow.auth.entity.User;
import com.factoryflow.auth.jwtUtils.AuthRequest;
import com.factoryflow.auth.jwtUtils.AuthResponse;
import com.factoryflow.auth.jwtUtils.JwtUtil;
import com.factoryflow.auth.repository.UserRepository;

@Service
public class AuthService implements IAuthService{

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	ModelMapper mapper;

	@PostMapping("/login")
	public AuthResponse login(AuthRequest request) {

		authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(

						request.getEmail(),

						request.getPassword()));

		User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

		String token = jwtUtil.generateToken(

				user.getEmail(),

				user.getRole(),

				user.getVendor().getId());
		return new 		AuthResponse(token,user.getVendor().getId(),user.getRole());

		
		
		

	}

}
