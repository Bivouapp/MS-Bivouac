package com.example.demo.controllers;

import com.example.demo.models.Bivouac;
import com.example.demo.models.BivouacEquipment;
import com.example.demo.models.Equipment;
import com.example.demo.repositories.BivouacEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bivouac-equipment")
public class BivouacEquipmentController {

    @Autowired
    private BivouacEquipmentRepository bivouacEquipmentRepository;

    @GetMapping("/bivouac/{bivouacId}")
    public ResponseEntity<List<Equipment>> getByBivouacId(@PathVariable Long bivouacId) {
        List<BivouacEquipment> bivouacEquipments = bivouacEquipmentRepository.findByBivouac_BivouacId(bivouacId);
        List<Equipment> equipments = bivouacEquipments.stream()
                .map(BivouacEquipment::getEquipment)
                .collect(Collectors.toList());
        return ResponseEntity.ok(equipments);
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Bivouac>> getByEquipmentId(@PathVariable Long equipmentId) {
        List<BivouacEquipment> bivouacEquipments = bivouacEquipmentRepository.findByEquipment_EquipmentId(equipmentId);
        List<Bivouac> bivouacs = bivouacEquipments.stream()
                .map(BivouacEquipment::getBivouac)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bivouacs);
    }

}
