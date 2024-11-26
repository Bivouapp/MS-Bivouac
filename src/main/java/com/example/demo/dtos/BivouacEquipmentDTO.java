package com.example.demo.dtos;

public class BivouacEquipmentDTO {
    private Long bivouacId;
    private Long equipmentId;

    public BivouacEquipmentDTO(Long bivouacId, Long equipmentId) {
        this.bivouacId = bivouacId;
        this.equipmentId = equipmentId;
    }

    // Getters and setters
    public Long getBivouacId() {
        return bivouacId;
    }

    public void setBivouacId(Long bivouacId) {
        this.bivouacId = bivouacId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }
}
