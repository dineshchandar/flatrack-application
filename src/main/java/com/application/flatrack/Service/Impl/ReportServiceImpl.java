package com.application.flatrack.Service.Impl;

import com.application.flatrack.Model.Apartment;
import com.application.flatrack.Model.MaintenanceRecord;
import com.application.flatrack.Repsository.ApartmentRepository;
import com.application.flatrack.Service.ReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    private Environment environment;


    @Override
    public void monthlyReport() {
        System.out.println("Generating Monthly Report");

                List<MaintenanceRecord> maintenanceRecords = getMaintenanceRecords();

        buildReport(maintenanceRecords);
    }

    private List<MaintenanceRecord> getMaintenanceRecords() {
        List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();

        apartmentRepository.findAll().forEach(apartment -> {
            MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
            maintenanceRecord.setFlatNo(apartment.getFlatNo());
            maintenanceRecord.setFloor(apartment.getFloor());
            maintenanceRecord.setOwnerName(apartment.getOwnerName());
            maintenanceRecord.setOccupiedBy(MaintenanceRecord.OccupiedBy.Owner);
            maintenanceRecord.setTenantName(apartment.getTenantName());
            maintenanceRecord.setAreaInSqft(apartment.getAreaInSqft());
            maintenanceRecord.setMaintenanceDate(LocalDateTime.now().toLocalDate());
            maintenanceRecord.setStandardMaintenanceAmount(calculateStdMaintenance(apartment));
            maintenanceRecord.setWaterMeterRent(150.0);
            maintenanceRecord.setWaterConsumption(25.0);
            maintenanceRecord.setWaterCharges(50.0);
            maintenanceRecord.setMaintenancePayable(1000.0);
            maintenanceRecord.setPaidLastMonth(2000.0);
            maintenanceRecord.setDuesAdjustments(0.0);
            maintenanceRecord.setTotalPayable(2150.0);
            maintenanceRecord.setComments("No comments");
            maintenanceRecords.add(maintenanceRecord);
        });
        return maintenanceRecords;
    }

    private Double calculateStdMaintenance(Apartment apartment) {
        String maintenanceFeeFlag = environment.getProperty("maintenance-fee-flag");
        if ("PER SQFT".equals(maintenanceFeeFlag)) {
            return apartment.getAreaInSqft() * 2.0; // Rs. 2 per sqft
        }
        return 2000.0; // Fixed amount
    }

    private static void buildReport(List<MaintenanceRecord> maintenanceRecords) {

        String[] columns = buildHeader();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Monthly Report");

            // Create a bold header style
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Create the header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (MaintenanceRecord maintenanceRecord : maintenanceRecords) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(maintenanceRecord.getFloor());
                row.createCell(1).setCellValue(maintenanceRecord.getFlatNo());
                row.createCell(2).setCellValue(maintenanceRecord.getOwnerName());
                row.createCell(3).setCellValue(maintenanceRecord.getTenantName() != null ?
                    maintenanceRecord.getTenantName() : maintenanceRecord.getOwnerName());
                row.createCell(4).setCellValue(maintenanceRecord.getOccupiedBy().toString());
                row.createCell(5).setCellValue(maintenanceRecord.getAreaInSqft());
                row.createCell(6).setCellValue(maintenanceRecord.getStandardMaintenanceAmount());
                row.createCell(7).setCellValue(maintenanceRecord.getWaterMeterRent());
                row.createCell(8).setCellValue(maintenanceRecord.getWaterConsumption());
                row.createCell(9).setCellValue(maintenanceRecord.getWaterCharges());
                row.createCell(10).setCellValue(maintenanceRecord.getMaintenancePayable());
                row.createCell(11).setCellValue(maintenanceRecord.getPaidLastMonth());
                row.createCell(12).setCellValue(maintenanceRecord.getDuesAdjustments());
                row.createCell(13).setCellValue(maintenanceRecord.getTotalPayable());
                row.createCell(14).setCellValue(maintenanceRecord.getComments());
            }


            // Auto-size all columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }


            try (FileOutputStream fileOut = new FileOutputStream(buildFileName())) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] buildHeader() {
        String[] columns = {
                "Floor", "Flat Number", "Owner Name", "Current Resident Name", "Occupied By",
                "Area", "Standard Maintenance Amount", "Water Meter Rent (Standard)",
                "Water Consumption", "Water Charges", "Maintenance Payable",
                "Paid Last Month", "Dues/Adjustments", "Total Payable", "Comments"
        };
        return columns;
    }

    private static String buildFileName() {
        String basePath = "D:\\dev\\flatrack-application\\src\\main\\resources\\output\\";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dateTime = LocalDateTime.now().format(formatter);
        System.out.println("Report will be saved at: " + basePath + "Monthly_Report_" + dateTime + ".xlsx");
        return basePath + "Monthly_Report_" + dateTime + ".xlsx";
    }
}
