package com.application.flatrack.Service.Impl;

import com.application.flatrack.Service.ReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReportServiceImpl implements ReportService {


    @Override
    public void monthlyReport() {
        System.out.println("Generating Monthly Report");

        String basePath = "D:\\dev\\flatrack-application\\src\\main\\resources\\output\\";

        // Add date and time to the file name
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dateTime = LocalDateTime.now().format(formatter);
        String fileName = "Monthly_Report" + "_" + dateTime + ".xlsx";

        String[] columns = {
                "Floor", "Flat Number", "Owner Name", "Current Resident Name", "Occupied By",
                "Area", "Standard Maintenance Amount", "Water Meter Rent (Standard)",
                "Water Consumption", "Water Charges", "Maintenance Payable",
                "Paid Last Month", "Dues/Adjustments", "Total Payable", "Comments"
        };

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

            // (Optional) Add sample data for now
            Object[][] sampleData = {
                    {"Ground", "001", "Thenappan", "Ved", "Tenant", 1310, 2000, 150,
                            25, 50, 2150, 2000, 0, 2150, "No comments"},
                    {"Ground", "002", "Balaji", "Balaji", "Owner", 1030, 2000, 150,
                            18, 36, 2136, 2000, 0, 2136, ""}
            };

            int rowNum = 1;
            for (Object[] rowData : sampleData) {
                Row row = sheet.createRow(rowNum++);
                for (int col = 0; col < rowData.length; col++) {
                    if (rowData[col] instanceof String) {
                        row.createCell(col).setCellValue((String) rowData[col]);
                    } else if (rowData[col] instanceof Integer) {
                        row.createCell(col).setCellValue((Integer) rowData[col]);
                    } else if (rowData[col] instanceof Double) {
                        row.createCell(col).setCellValue((Double) rowData[col]);
                    }
                }
            }

            // Auto-size all columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(basePath + fileName)) {
                workbook.write(fileOut);
            }

            System.out.println(fileName + " generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
