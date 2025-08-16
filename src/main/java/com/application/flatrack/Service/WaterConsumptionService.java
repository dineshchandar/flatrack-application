package com.application.flatrack.Service;

import com.application.flatrack.Model.Dbo.WaterConsumptionRecord;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface WaterConsumptionService {
    void loadWaterConsumption(MultipartFile waterConsumptionFile);

    List<WaterConsumptionRecord> monthlyWaterReport(LocalDate reportDate);
}
