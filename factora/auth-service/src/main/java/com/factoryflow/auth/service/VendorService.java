package com.factoryflow.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factoryflow.auth.InterfaceService.IVendorService;
import com.factoryflow.auth.dao.VendorDAO;
import com.factoryflow.auth.dto.VendorDTO;
import com.factoryflow.auth.entity.Vendor;

@Service
public class VendorService implements IVendorService{
	@Autowired
	VendorDAO dao;
	@Autowired
	ModelMapper mapper;

	public VendorDTO registerVendor(VendorDTO vendor) {

		Vendor map = mapper.map(vendor, Vendor.class);
		Vendor registerVendor = dao.registerVendor(map);

		return mapper.map(registerVendor, VendorDTO.class);

	}}