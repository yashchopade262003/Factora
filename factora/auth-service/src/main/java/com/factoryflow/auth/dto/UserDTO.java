package com.factoryflow.auth.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;

	private String name;

	private String email;

	private String password;

	private String role;

	private String address;

	private LocalDateTime createsAt;

	private boolean status;
}
