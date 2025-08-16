package com.application.flatrack.Model.Dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
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

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "dues_adjustments")
    private Double duesAdjustments;

    @Column(name = "total_payable")
    private Double totalPayable;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "comments", length = 500)
    private String comments;

    // --- Enum for Occupied By ---
    public enum OccupiedBy {
        Owner, Tenant
    }
}
