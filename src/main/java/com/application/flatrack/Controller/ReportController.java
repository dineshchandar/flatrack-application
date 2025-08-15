package com.application.flatrack.Controller;

import com.application.flatrack.Service.Impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/v1/report")
public class ReportController {

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping("/monthly")
    public void monthlyReport() {
        reportService.monthlyReport();
    }
}
