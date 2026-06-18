package com.factoryflow.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoryflow.auth.dto.AuthRequest;
import com.factoryflow.auth.dto.AuthResponse;
import com.factoryflow.auth.dto.OTPRequest;
import com.factoryflow.auth.dto.OTPRequrstVerify;
import com.factoryflow.auth.jwtUtils.JwtUtil;
import com.factoryflow.auth.service.OTPService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    OTPService otpService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        String token =
                jwtUtil.generateToken(
                        request.getEmail());

        return ResponseEntity.ok(
                new AuthResponse(token));
    }
    
    @PostMapping("/send-otp")
    public String sendOTP(
            @RequestBody OTPRequest request) {

        return otpService.sendOTP(request.getEmail());
    }
    @PostMapping("/verify-otp")
    public AuthResponse verifyOTP(
            @RequestBody OTPRequrstVerify request) {

        return otpService.verifyOTP(
                request.getEmail(),
                request.getOtp());
    }
    
    
    
}