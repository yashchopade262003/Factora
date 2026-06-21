package org.inventry.service.controller;

import java.util.List;

import org.inventry.service.ResponceEntity.ResponseStructure;
import org.inventry.service.dto.InventoryDTO;
import org.inventry.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<InventoryDTO>> saveInventory(
            @RequestBody InventoryDTO dto) {

        return inventoryService.saveInventory(dto);
    }

    @GetMapping("/material/{materialCode}")
    public ResponseEntity<ResponseStructure<InventoryDTO>> findByMaterialCode(
            @PathVariable String materialCode) {

        return inventoryService.findByMaterialCode(materialCode);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<InventoryDTO>>> getAllInventories() {

        return inventoryService.getAllInventories();
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<InventoryDTO>> getInventoryById(
            @PathVariable Long id) {

        return inventoryService.getInventoryById(id);
    }

   
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<InventoryDTO>> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryDTO dto) {

        return inventoryService.updateInventory(id, dto);
    }

   
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteInventory(
            @PathVariable Long id) {

        return inventoryService.deleteInventory(id);
    }

   
    @GetMapping("/count")
    public ResponseEntity<ResponseStructure<Long>> getInventoryCount() {

        return inventoryService.getInventoryCount();
    }

   
    @GetMapping("/warehouse/{location}")
    public ResponseEntity<ResponseStructure<List<InventoryDTO>>> findByWarehouseLocation(
            @PathVariable String location) {

        return inventoryService.findByWarehouse(location);
    }
}