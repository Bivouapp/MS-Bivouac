package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "bivouac_equipments")
@IdClass(BivouacEquipmentId.class)
public class BivouacEquipment {

    @Id
    @ManyToOne
    @JoinColumn(name = "bivouac_id")
    private Bivouac bivouac;

    @Id
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    // Getters et setters

    public Bivouac getBivouac() {
        return bivouac;
    }

    public void setBivouac(Bivouac bivouac) {
        this.bivouac = bivouac;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}