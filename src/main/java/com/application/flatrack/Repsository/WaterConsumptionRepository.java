package com.application.flatrack.Repsository;


import com.application.flatrack.Model.Dbo.WaterConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public interface WaterConsumptionRepository extends JpaRepository<WaterConsumptionRecord, Long> {

    Logger log = LoggerFactory.getLogger(WaterConsumptionRepository.class);

    @Query(value = "SELECT * FROM water_consumption_master w " +
            "WHERE w.meter_no IS NULL " +
            "AND MONTH(w.reading_date) = MONTH(:reportDate) " +
            "AND YEAR(w.reading_date) = YEAR(:reportDate)", nativeQuery = true)
    List<WaterConsumptionRecord> getMonthlyConsumption(@Param("reportDate") LocalDate reportDate);

}
