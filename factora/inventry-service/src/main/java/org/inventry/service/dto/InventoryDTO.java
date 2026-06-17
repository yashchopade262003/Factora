package org.inventry.service.dto;

import java.time.LocalDate;

public class InventoryDTO {
	private Long inventoryId;

    private String materialName;

    private String materialCode;

    private Double quantity;

    private String unit;

    private String warehouseLocation;

    private LocalDate receivedDate;

    private String status;
}
