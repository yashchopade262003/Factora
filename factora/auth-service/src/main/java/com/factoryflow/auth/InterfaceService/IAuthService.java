package com.factoryflow.auth.InterfaceService;

import com.factoryflow.auth.jwtUtils.AuthRequest;
import com.factoryflow.auth.jwtUtils.AuthResponse;

public interface IAuthService {
	public AuthResponse login(AuthRequest request) ;
}
