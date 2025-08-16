package com.application.flatrack.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_record",
        indexes = @Index(name = "idx_flat_report_maint", columnList = "flat_no, report_date, maintenance_date"))
public class MaintenanceRecord {

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

    @Column(name = "report_date")
    private LocalDate reportDate;

    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;

    @Column(name = "standard_maintenance_amount")
    private Double standardMaintenanceAmount;

    @Column(name = "water_meter_rent")
    private Double waterMeterRent;

    @Column(name = "water_consumption")
    private Double waterConsumption;

    @Column(name = "water_charges")
    private Double waterCharges;

    @Column(name = "maintenance_payable")
    private Double maintenancePayable;

    @Column(name = "paid_last_month")
    private Double paidLastMonth;

    @Column(name = "dues_adjustments")
    private Double duesAdjustments;

    @Column(name = "total_payable")
    private Double totalPayable;

    @Column(name = "comments", length = 500)
    private String comments;

    // --- Enum for Occupied By ---
    public enum OccupiedBy {
        Owner, Tenant
    }

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

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Double getStandardMaintenanceAmount() {
        return standardMaintenanceAmount;
    }

    public void setStandardMaintenanceAmount(Double standardMaintenanceAmount) {
        this.standardMaintenanceAmount = standardMaintenanceAmount;
    }

    public Double getWaterMeterRent() {
        return waterMeterRent;
    }

    public void setWaterMeterRent(Double waterMeterRent) {
        this.waterMeterRent = waterMeterRent;
    }

    public Double getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(Double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public Double getWaterCharges() {
        return waterCharges;
    }

    public void setWaterCharges(Double waterCharges) {
        this.waterCharges = waterCharges;
    }

    public Double getMaintenancePayable() {
        return maintenancePayable;
    }

    public void setMaintenancePayable(Double maintenancePayable) {
        this.maintenancePayable = maintenancePayable;
    }

    public Double getPaidLastMonth() {
        return paidLastMonth;
    }

    public void setPaidLastMonth(Double paidLastMonth) {
        this.paidLastMonth = paidLastMonth;
    }

    public Double getDuesAdjustments() {
        return duesAdjustments;
    }

    public void setDuesAdjustments(Double duesAdjustments) {
        this.duesAdjustments = duesAdjustments;
    }

    public Double getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(Double totalPayable) {
        this.totalPayable = totalPayable;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
