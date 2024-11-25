package com.example.demo.controllers;

import com.example.demo.models.BivouacEquipment;
import com.example.demo.repositories.BivouacEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bivouac-equipment")
public class BivouacEquipmentController {

    @Autowired
    private BivouacEquipmentRepository bivouacEquipmentRepository;

    @GetMapping("/bivouac/{bivouacId}")
    public ResponseEntity<List<BivouacEquipment>> getByBivouacId(@PathVariable Long bivouacId) {
        List<BivouacEquipment> bivouacEquipments = bivouacEquipmentRepository.findByBivouac_BivouacId(bivouacId);
        return ResponseEntity.ok(bivouacEquipments);
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<BivouacEquipment>> getByEquipmentId(@PathVariable Long equipmentId) {
        List<BivouacEquipment> bivouacEquipments = bivouacEquipmentRepository.findByEquipment_EquipmentId(equipmentId);
        return ResponseEntity.ok(bivouacEquipments);
    }

}
