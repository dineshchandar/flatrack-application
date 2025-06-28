package com.application.flatrack.Repsository;


import com.application.flatrack.Model.BankStatementRecord;
import com.application.flatrack.Model.WaterConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterConsumptionRepository extends JpaRepository<WaterConsumptionRecord, Long> {
}
