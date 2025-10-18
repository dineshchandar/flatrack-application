package com.application.flatrack.Controller;

import com.application.flatrack.Requests.MonthlyReportRequest;
import com.application.flatrack.Service.Impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/web/v1/report")
public class ReportController {

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping("/monthly")
    public void monthlyReport(@RequestBody MonthlyReportRequest request ) {

        reportService.monthlyReport(request);

    }

    @GetMapping("/monthlyformatted")
    public void monthlyReportFormatted(@RequestBody MonthlyReportRequest request ) {

        reportService.monthlyReportFormatted(request);

    }


}
