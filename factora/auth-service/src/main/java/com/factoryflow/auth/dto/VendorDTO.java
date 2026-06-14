package com.factoryflow.auth.dto;

import lombok.Data;

@Data
public class VendorDTO {

	private Long id;
	private String companyname;
	private String factoryname;
	private String email;
	private String phoneno;
	private String address;
	private boolean status;
}
