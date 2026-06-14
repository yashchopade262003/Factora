package com.factoryflow.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity 
@Data
public class Vendor {

	
	@Id 
	@GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "sequence")
    @SequenceGenerator(
    		name = "sequence",
    		allocationSize = 1)
	private Long id;
	private String companyname;
	private String factoryname;
	private String email;
	private String phoneno;
	private String address;
	private boolean status;
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	@PrePersist
	public void createdTime() {
		this.createAt=LocalDateTime.now();
	}
	
	
	
	
}
