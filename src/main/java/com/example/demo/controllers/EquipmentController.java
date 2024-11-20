package com.example.demo.controllers;

import com.example.demo.models.Equipment;
import com.example.demo.repositories.EquipmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping
    public ResponseEntity<List<Equipment>> list () {
        List<Equipment> equipements = equipmentRepository.findAll();
        return ResponseEntity.ok(equipements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> get(@PathVariable Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment with ID " + id + " not found"));
        return ResponseEntity.ok(equipment);  // Retourne une réponse avec code 200 OK
    }

    @PostMapping
    public ResponseEntity<Equipment> create(@RequestBody final Equipment equipment) {
        Equipment savedEquipment =  equipmentRepository.saveAndFlush(equipment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipment);  // Retourne une réponse avec code 201 Created
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment with ID " + id + " not found"));
        equipmentRepository.delete(equipment);
        return ResponseEntity.noContent().build();  // Retourne une réponse avec code 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable Long id, @RequestBody Equipment equipment) {
        Equipment existingEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment with ID " + id + " not found"));
        BeanUtils.copyProperties(equipment, existingEquipment, "equipement_id");
        Equipment updatedEquipment = equipmentRepository.saveAndFlush(existingEquipment);
        return ResponseEntity.ok(updatedEquipment);  // Retourne une réponse avec code 200 OK
    }

}
