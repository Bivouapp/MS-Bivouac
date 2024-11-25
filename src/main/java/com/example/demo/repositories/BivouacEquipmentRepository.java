package com.example.demo.repositories;

import com.example.demo.models.BivouacEquipment;
import com.example.demo.models.BivouacEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BivouacEquipmentRepository extends JpaRepository<BivouacEquipment, BivouacEquipmentId> {
    List<BivouacEquipment> findByBivouac_BivouacId(Long bivouacId);
    List<BivouacEquipment> findByEquipment_EquipmentId(Long equipmentId);
}
