package com.factoryflow.auth.dto;

import lombok.Data;

@Data
public class OTPRequrstVerify {

	String Email;
	String otp;
}
