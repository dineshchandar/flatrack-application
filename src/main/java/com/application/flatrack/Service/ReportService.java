package com.application.flatrack.Service;


import com.application.flatrack.Requests.MonthlyReportRequest;

public interface ReportService {
    void monthlyReport(MonthlyReportRequest reportDate);
}
