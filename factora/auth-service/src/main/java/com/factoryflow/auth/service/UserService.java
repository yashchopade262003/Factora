package com.factoryflow.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.factoryflow.auth.InterfaceService.IUserService;
import com.factoryflow.auth.dao.UserDAO;
import com.factoryflow.auth.dto.UserDTO;
import com.factoryflow.auth.entity.User;
import com.factoryflow.auth.entity.Vendor;
import com.factoryflow.auth.repository.VendorRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ADD THIS
	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public UserDTO registerUser(UserDTO user) {

		User userentity = mapper.map(user, User.class);

		// password encode
		userentity.setPassword(passwordEncoder.encode(userentity.getPassword()));

		// IMPORTANT PART
		Vendor vendor = vendorRepository.findById(user.getVendorId()).orElseThrow();

		// SET VENDOR
		userentity.setVendor(vendor);

		User registerUser = userDAO.registerUser(userentity);

		UserDTO userdto = mapper.map(registerUser, UserDTO.class);

		return userdto;
	}
}