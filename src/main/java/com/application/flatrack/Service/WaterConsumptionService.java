package com.application.flatrack.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface WaterConsumptionService {
    void loadWaterConsumption(MultipartFile waterConsumptionFile);
}
