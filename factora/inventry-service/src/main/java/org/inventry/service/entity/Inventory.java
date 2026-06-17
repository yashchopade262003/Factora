package org.inventry.service.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long vendorid;
    
    private Long warehouseId;
    
    private String materialCode;
    
    private String materialName;

private String materialCategory;

    private Double quantity;

    private String unit;

    private String warehouseLocation;

    private LocalDate receivedDate;

    private String status;
}