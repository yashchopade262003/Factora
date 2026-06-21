package org.inventry.service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.inventry.service.ResponceEntity.ResponseStructure;
import org.inventry.service.dao.InventoryDAO;
import org.inventry.service.dto.InventoryDTO;
import org.inventry.service.entity.Inventory;
import org.inventry.service.entity.InventoryStatus;
import org.inventry.service.exception.InventoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

	@Autowired
	private InventoryDAO inventoryDAO;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<InventoryDTO>> saveInventory(InventoryDTO dto) {
		try {
			Inventory inventory = new Inventory();

			// Manual Mapping
			inventory.setVendorId(dto.getVendorId());
			inventory.setWarehouseId(dto.getWarehouseId());
			inventory.setMaterialCode(dto.getMaterialCode());
			inventory.setMaterialName(dto.getMaterialName());
			inventory.setMaterialCategory(dto.getMaterialCategory());
			inventory.setQuantity(dto.getQuantity());
			inventory.setUnit(dto.getUnit());
			inventory.setUnitPrice(dto.getUnitPrice());
			inventory.setWarehouseLocation(dto.getWarehouseLocation());
			inventory.setSupplierId(dto.getSupplierId());
			inventory.setBatchNumber(dto.getBatchNumber());
			inventory.setManufacturingDate(dto.getManufacturingDate());
			inventory.setExpiryDate(dto.getExpiryDate());
			inventory.setReceivedDate(dto.getReceivedDate());
			inventory.setMinimumStockLevel(dto.getMinimumStockLevel());
			inventory.setRemarks(dto.getRemarks());

			inventory.setCreatedAt(LocalDateTime.now().toString());
			// Audit
			inventory.setUpdatedAt(java.time.LocalDateTime.now());

			// Business Logic
			inventory.setTotalValue(inventory.getQuantity() * inventory.getUnitPrice());

			if (inventory.getQuantity() == 0) {
				inventory.setStatus(InventoryStatus.OUT_OF_STOCK);
			} else if (inventory.getQuantity() <= inventory.getMinimumStockLevel()) {
				inventory.setStatus(InventoryStatus.LOW_STOCK);
			} else {
				inventory.setStatus(InventoryStatus.AVAILABLE);
			}

			Inventory savedInventory = inventoryDAO.saveInventory(inventory);

			// Manual DTO Mapping
			InventoryDTO responseDTO = new InventoryDTO();

			responseDTO.setVendorId(savedInventory.getVendorId());
			responseDTO.setWarehouseId(savedInventory.getWarehouseId());
			responseDTO.setMaterialCode(savedInventory.getMaterialCode());
			responseDTO.setMaterialName(savedInventory.getMaterialName());
			responseDTO.setMaterialCategory(savedInventory.getMaterialCategory());
			responseDTO.setQuantity(savedInventory.getQuantity());
			responseDTO.setUnit(savedInventory.getUnit());
			responseDTO.setUnitPrice(savedInventory.getUnitPrice());
			responseDTO.setWarehouseLocation(savedInventory.getWarehouseLocation());
			responseDTO.setSupplierId(savedInventory.getSupplierId());
			responseDTO.setBatchNumber(savedInventory.getBatchNumber());
			responseDTO.setManufacturingDate(savedInventory.getManufacturingDate());
			responseDTO.setExpiryDate(savedInventory.getExpiryDate());
			responseDTO.setReceivedDate(savedInventory.getReceivedDate());
			responseDTO.setMinimumStockLevel(savedInventory.getMinimumStockLevel());
			responseDTO.setRemarks(savedInventory.getRemarks());

			ResponseStructure<InventoryDTO> response = new ResponseStructure<>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Inventory Added Successfully");
			response.setData(responseDTO);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;

	}

	public ResponseEntity<ResponseStructure<List<InventoryDTO>>> getAllInventories() {

		List<Inventory> inventories = inventoryDAO.getAllInventries();

		List<InventoryDTO> dtoList = inventories.stream().map(inventory -> mapper.map(inventory, InventoryDTO.class))
				.toList();

		ResponseStructure<List<InventoryDTO>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Inventories");
		response.setData(dtoList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<InventoryDTO>> getInventoryById(Long id) {

		Optional<Inventory> optional = inventoryDAO.getInventoryById(id);

		if (optional.isEmpty()) {
			throw new InventoryException("Inventory Not Found");
		}

		InventoryDTO dto = mapper.map(optional.get(), InventoryDTO.class);

		ResponseStructure<InventoryDTO> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Inventory Found");
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<InventoryDTO>> updateInventory(Long id, InventoryDTO dto) {

		Optional<Inventory> optional = inventoryDAO.getInventoryById(id);

		if (optional.isEmpty()) {
			throw new InventoryException("Inventory Not Found");
		}

		Inventory inventory = optional.get();

		mapper.map(dto, inventory);

		inventory.setInventoryId(id);

		inventory.setTotalValue(inventory.getQuantity() * inventory.getUnitPrice());

		if (inventory.getQuantity() == 0) {
			inventory.setStatus(InventoryStatus.OUT_OF_STOCK);
		} else if (inventory.getQuantity() <= inventory.getMinimumStockLevel()) {
			inventory.setStatus(InventoryStatus.LOW_STOCK);
		} else {
			inventory.setStatus(InventoryStatus.AVAILABLE);
		}

		Inventory updatedInventory = inventoryDAO.saveInventory(inventory);

		InventoryDTO responseDTO = mapper.map(updatedInventory, InventoryDTO.class);

		ResponseStructure<InventoryDTO> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Inventory Updated Successfully");
		response.setData(responseDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteInventory(Long id) {

		boolean deleted = inventoryDAO.deleteById(id);

		if (!deleted) {
			throw new InventoryException("Inventory Not Found");
		}

		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Inventory Deleted Successfully");
		response.setData("Deleted");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Long>> getInventoryCount() {

		long count = inventoryDAO.getStockOfInventry();

		ResponseStructure<Long> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Inventory Count");
		response.setData(count);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<InventoryDTO>>> findByWarehouse(String location) {

		List<Inventory> inventories = inventoryDAO.findByWareHouseLocation(location);

		List<InventoryDTO> dtoList = inventories.stream().map(inventory -> mapper.map(inventory, InventoryDTO.class))
				.toList();

		ResponseStructure<List<InventoryDTO>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Warehouse Inventory");
		response.setData(dtoList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<InventoryDTO>> findByMaterialCode(String code) {
		Optional<Inventory> optional = inventoryDAO.findByIdMaterialCode(code);
		if (optional.isEmpty()) {
			throw new InventoryException("Material Code Not Found");
		}
		InventoryDTO dto = mapper.map(optional.get(), InventoryDTO.class);
		ResponseStructure<InventoryDTO> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Inventory Found");
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}