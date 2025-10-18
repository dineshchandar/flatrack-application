package com.application.flatrack.Model.Other;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MonthlyWaterConsumptionRecord {
    LocalDate date;
    String flatNumber;
    Double waterConsumption;
}
