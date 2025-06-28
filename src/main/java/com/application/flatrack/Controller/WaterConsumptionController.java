package com.application.flatrack.Controller;

import com.application.flatrack.Service.Impl.WaterConsumptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/web/v1/waterconsumption")
public class WaterConsumptionController {

    @Autowired
    private WaterConsumptionServiceImpl waterConsumptionService;

    @PostMapping("/load")
    public void loadWaterConsumption(@RequestParam("file") MultipartFile file) {
        waterConsumptionService.loadWaterConsumption(file);
    }
}
