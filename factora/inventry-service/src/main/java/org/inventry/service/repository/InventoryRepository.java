package org.inventry.service.repository;

import java.util.List;
import java.util.Optional;

import org.inventry.service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	 Optional<Inventory> findByMaterialCode(String materialCode);

	 List<Inventory> findByWarehouseLocation(String location);

}
