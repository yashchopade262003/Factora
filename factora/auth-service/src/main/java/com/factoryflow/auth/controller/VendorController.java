package com.factoryflow.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factoryflow.auth.InterfaceService.IVendorService;
import com.factoryflow.auth.dto.VendorDTO;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private IVendorService service;

    @PostMapping("/register")
    public VendorDTO registerVendor(
            @RequestBody VendorDTO vendor) {
    		
    	return service.registerVendor(vendor);
    	
    }
}