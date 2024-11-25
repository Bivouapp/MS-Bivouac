package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="equipments")
@Access(AccessType.FIELD)
public class Equipment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private long equipmentId;

    private String label;
    private String icon;

    @OneToMany(mappedBy = "equipment")
    private Set<BivouacEquipment> bivouacEquipments = new HashSet<>();

    public long getEquipmentId() {
        return equipmentId;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return icon;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
