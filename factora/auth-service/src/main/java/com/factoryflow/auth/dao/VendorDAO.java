package com.factoryflow.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.factoryflow.auth.entity.Vendor;
import com.factoryflow.auth.repository.VendorRepository;
@Repository
public class VendorDAO {

	@Autowired
	private VendorRepository repository;
	
	 public Vendor registerVendor(
	            Vendor vendor) {

	        return repository.save(vendor);
	    }
}
