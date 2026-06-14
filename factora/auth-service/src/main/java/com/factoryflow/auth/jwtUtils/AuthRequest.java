package com.factoryflow.auth.jwtUtils;


import lombok.Data;

@Data
public class AuthRequest {

    private String email;

    private String password;
}