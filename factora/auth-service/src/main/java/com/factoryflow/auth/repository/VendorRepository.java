package com.factoryflow.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.factoryflow.auth.entity.Vendor;
@Repository
public interface VendorRepository  extends JpaRepository<Vendor, Long>{

}
