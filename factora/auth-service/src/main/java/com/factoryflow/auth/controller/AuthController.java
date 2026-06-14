package com.factoryflow.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoryflow.auth.InterfaceService.IAuthService;
import com.factoryflow.auth.jwtUtils.AuthRequest;
import com.factoryflow.auth.jwtUtils.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

   IAuthService authService;

    @PostMapping("/login")
   public AuthResponse login(@RequestBody AuthRequest request) {
    return 	authService.login(request);
    }
}