package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="bivouacs")
@Access(AccessType.FIELD)
public class Bivouac {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long bivouac_id;
    @Column(name = "host_id")
    private long hostId;
    private String name;
    private float price;
    @Column(name = "rental_type")
    private String rentalType;
    @Column(name = "field_type")
    private String fieldType;
    private float area;
    private String description;
    @Column(name = "is_pmr")
    private boolean isPmr;
    private String privacy;

    @ManyToMany
    @JoinTable(name = "bivouac_equipments",joinColumns = @JoinColumn(name = "bivouac_id"),inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Equipment> equipments = new HashSet<>();

    public long getBivouac_id() {
        return bivouac_id;
    }

    public long getHostId() {
        return hostId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getRental_type() {
        return rentalType;
    }

    public String getField_type() {
        return fieldType;
    }

    public float getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIs_pmr() {
        return isPmr;
    }

    public String getPrivacy() {
        return privacy;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setBivouac_id(long bivouac_id) {
        this.bivouac_id = bivouac_id;
    }

    public void setHostId(long host_id) {
        this.hostId = host_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRental_type(String rentalType) {
        this.rentalType = rentalType;
    }

    public void setField_type(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_pmr(boolean is_pmr) {
        this.isPmr = is_pmr;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

}
