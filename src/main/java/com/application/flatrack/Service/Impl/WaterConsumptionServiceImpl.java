package com.application.flatrack.Service.Impl;

import com.application.flatrack.Repsository.BankStatementRepository;
import com.application.flatrack.Service.WaterConsumptionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WaterConsumptionServiceImpl implements WaterConsumptionService {

    @Autowired
    BankStatementRepository bankStatementRepository;

    @Override
    public void loadWaterConsumption(MultipartFile waterConsumptionFile) {
        try (InputStream is = waterConsumptionFile.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // Extract date headers from column 5 onwards (index 4)
            List<LocalDate> dateColumns = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH);

            for (int col = 4; col < headerRow.getLastCellNum(); col++) {
                Cell cell = headerRow.getCell(col);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String dateStr = cell.getStringCellValue().trim();
                    LocalDate date = LocalDate.parse(dateStr, formatter).withYear(LocalDate.now().getYear());
                    dateColumns.add(date);
                }
            }

            // Process each data row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                String apartment = getCellValue(row.getCell(0));
                String owner = getCellValue(row.getCell(1));
                String location = getCellValue(row.getCell(2));
                String meterNo = getCellValue(row.getCell(3));

                for (int i = 0; i < dateColumns.size(); i++) {
                    Cell readingCell = row.getCell(4 + i);
                    if (readingCell == null || readingCell.getCellType() != CellType.NUMERIC) continue;

                    double reading = readingCell.getNumericCellValue();
                    LocalDate readingDate = dateColumns.get(i);

                    // Replace with save to DB or model object
                    System.out.printf("Apartment: %s | Date: %s | Reading: %.2f\n",
                            apartment, readingDate, reading);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file: " + e.getMessage());
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> null;
        };
    }
}
