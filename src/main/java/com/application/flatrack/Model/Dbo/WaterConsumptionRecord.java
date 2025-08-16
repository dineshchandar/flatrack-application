package com.application.flatrack.Model.Dbo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "water_consumption_master")
@Getter
@Setter
public class WaterConsumptionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apartment", length = 10)
    private String apartment;

    @Column(name = "owner", length = 100)
    private String owner;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "meter_no", length = 50)
    private String meterNo;

    @Column(name = "reading_date")
    private LocalDate readingDate;

    @Column(name = "reading_value")
    private Double readingValue;
}