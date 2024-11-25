package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

public class BivouacEquipmentId implements Serializable {
    private Long bivouac;
    private Long equipment;

    // Constructeurs, getters, setters, equals() et hashCode()
    public BivouacEquipmentId() {}

    public BivouacEquipmentId(Long bivouac, Long equipment) {
        this.bivouac = bivouac;
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BivouacEquipmentId that = (BivouacEquipmentId) o;
        return Objects.equals(bivouac, that.bivouac) &&
                Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bivouac, equipment);
    }
}
