package com.factoryflow.auth.InterfaceService;

import com.factoryflow.auth.dto.AuthResponse;

public interface IOTPService {

    String sendOTP(String phone);

    AuthResponse verifyOTP(String phone, String otp);
}
