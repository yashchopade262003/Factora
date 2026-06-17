package org.inventry.service.dao;

import java.util.List;
import java.util.Optional;

import org.inventry.service.entity.Inventory;
import org.inventry.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDAO {
	@Autowired
	InventoryRepository inventoryRepository;

	public Inventory saveInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	public Optional<Inventory> findByIdMaterialCode(String materialCode) {
		return inventoryRepository.findByMaterialCode(materialCode);

	}
	
	public List<Inventory> getAllInventries() {
		return  inventoryRepository.findAll();
	}
	
	public boolean deleteById(Long id) {
		
		Optional<Inventory> byId = inventoryRepository.findById(id);
		if(byId.isPresent()) {
			
			inventoryRepository.deleteById(id);
			return true;
		}
		return false;
		
	}
	
	public long getStockOfInventry() {
		return inventoryRepository.count();
	}
	
	public List<Inventory> findByWareHouseLocation(String location) {
		return inventoryRepository.findByWarehouseLocation(location);
	}
}
