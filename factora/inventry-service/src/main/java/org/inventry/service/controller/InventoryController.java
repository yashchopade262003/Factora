package org.inventry.service.controller;

import java.util.List;

import org.inventry.service.ResponceEntity.ResponceStructure;
import org.inventry.service.dto.InventoryDTO;
import org.inventry.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@GetMapping("/save")
	public ResponseEntity<ResponceStructure<InventoryDTO>> saveInventory(@RequestBody InventoryDTO dto) {
		return inventoryService.saveInventory(dto);
	}

	@GetMapping("/material/{code}")
	public ResponseEntity<ResponceStructure<InventoryDTO>> findByMaterialCode(@PathVariable String code) {
		return inventoryService.findByMaterialCode(code);
	}

	@GetMapping("/getall")
	public ResponseEntity<ResponceStructure<List<InventoryDTO>>> getAllInventries() {
		return inventoryService.getAllInventries();
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponceStructure<InventoryDTO>> deleteById(@PathVariable Long id) {
		return inventoryService.deleteById(id);
	}
	
	@GetMapping("/stock")
	public ResponseEntity<ResponceStructure<Long>> getStockCount() {
	return 	inventoryService.getStockCount();
	}
	
	@GetMapping("/location/{loc}")
	public ResponseEntity<ResponceStructure<List<InventoryDTO>>> findByWarehouseLocation(@PathVariable String loc) {
		  return  inventoryService.FindByWarehouseLocation(loc);
	}
	
}
