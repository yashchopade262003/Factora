package org.inventry.service.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.inventry.service.ResponceEntity.ResponceStructure;
import org.inventry.service.dao.InventoryDAO;
import org.inventry.service.dto.InventoryDTO;
import org.inventry.service.entity.Inventory;
import org.inventry.service.exception.InventoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
	@Autowired
	InventoryDAO inventoryDAO;
	@Autowired
	ModelMapper mapper;

	public ResponseEntity<ResponceStructure<InventoryDTO>> saveInventory(InventoryDTO inventoryDTO) {
		Inventory inventry = mapper.map(inventoryDTO, Inventory.class);
		Inventory saveInventory = inventoryDAO.saveInventory(inventry);

		InventoryDTO map = mapper.map(saveInventory, InventoryDTO.class);
		if (map != null) {

			ResponceStructure<InventoryDTO> responceStructure = new ResponceStructure<>();
			responceStructure.setStatusCode(HttpStatus.OK.value());
			responceStructure.setMessage("saved");
			responceStructure.setData(map);

			return new ResponseEntity<ResponceStructure<InventoryDTO>>(responceStructure, HttpStatus.OK);
		}

		throw new InventoryException("Not stored");

	}

	public ResponseEntity<ResponceStructure<InventoryDTO>> findByMaterialCode(String materialCode) {
		Optional<Inventory> byIdMaterialCode = inventoryDAO.findByIdMaterialCode(materialCode);

		if (byIdMaterialCode.isPresent()) {
			Inventory inventory = byIdMaterialCode.get();
			InventoryDTO map = mapper.map(inventory, InventoryDTO.class);

			ResponceStructure<InventoryDTO> responceStructure = new ResponceStructure<>();
			responceStructure.setStatusCode(HttpStatus.OK.value());
			responceStructure.setMessage("Inventory found ");
			responceStructure.setData(map);

			return new ResponseEntity<ResponceStructure<InventoryDTO>>(responceStructure, HttpStatus.OK);

		}
		throw new InventoryException(materialCode);
	}

	public ResponseEntity<ResponceStructure<List<InventoryDTO>>> getAllInventries() {

		List<Inventory> allInventries = inventoryDAO.getAllInventries();

		if (!allInventries.isEmpty()) {

			List<InventoryDTO> inventoryDTOs = allInventries.stream()
					.map(inventory -> mapper.map(inventory, InventoryDTO.class)).toList();

			ResponceStructure<List<InventoryDTO>> responseStructure = new ResponceStructure<>();

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("All inventories found");
			responseStructure.setData(inventoryDTOs);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}

		throw new InventoryException("No inventories found");
	}

	public ResponseEntity<ResponceStructure<InventoryDTO>> deleteById(Long id) {
		boolean deleteById = inventoryDAO.deleteById(id);
		if (deleteById) {

			ResponceStructure<InventoryDTO> responceStructure = new ResponceStructure<>();
			responceStructure.setData(null);
			responceStructure.setMessage("deleted sucessfully");
			responceStructure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponceStructure<InventoryDTO>>(responceStructure, HttpStatus.OK);
		}
		ResponceStructure<InventoryDTO> responceStructure = new ResponceStructure<>();
		responceStructure.setData(null);
		responceStructure.setMessage("id not found");
		responceStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponceStructure<InventoryDTO>>(responceStructure, HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<ResponceStructure<Long>> getStockCount() {

		long count = inventoryDAO.getStockOfInventry();

		ResponceStructure<Long> responseStructure = new ResponceStructure<>();

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Stock count fetched ");
		responseStructure.setData(count);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponceStructure<List<InventoryDTO>>> FindByWarehouseLocation(String location) {
		List<Inventory> byWareHouseLocation = inventoryDAO.findByWareHouseLocation(location);

		if (!byWareHouseLocation.isEmpty()) {

			List<InventoryDTO> inventoryDTOs = byWareHouseLocation.stream()
					.map(inventory -> mapper.map(inventory, InventoryDTO.class)).toList();

			ResponceStructure<List<InventoryDTO>> responseStructure = new ResponceStructure<>();

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("All inventories found");
			responseStructure.setData(inventoryDTOs);
			
			return new  ResponseEntity<ResponceStructure<List<InventoryDTO>>>(responseStructure,HttpStatus.OK);
		}
		
		ResponceStructure<List<InventoryDTO>> responseStructure = new ResponceStructure<>();

		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All inventories found");
		responseStructure.setData(null);
		
		return new  ResponseEntity<ResponceStructure<List<InventoryDTO>>>(responseStructure,HttpStatus.OK);
		
	}
}
