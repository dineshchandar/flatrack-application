package com.application.flatrack.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "apartment_master")
public class Apartment {
    @Id
    @Column(name = "flat_no", length = 10)
    private String flatNo;

    @Column(name = "floor", length = 50)
    private String floor;

    @Column(name = "owner_name", length = 100)
    private String ownerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupied_by", length = 10)
    private OccupiedBy occupiedBy;

    @Column(name = "tenant_name", length = 100)
    private String tenantName;

    @Column(name = "area_in_sqft")
    private Integer areaInSqft;

    // --- Getters and Setters ---

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public OccupiedBy getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(OccupiedBy occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Integer getAreaInSqft() {
        return areaInSqft;
    }

    public void setAreaInSqft(Integer areaInSqft) {
        this.areaInSqft = areaInSqft;
    }

    // --- Enum for OccupiedBy ---
    public enum OccupiedBy {
        Owner, Tenant, None
    }
}
