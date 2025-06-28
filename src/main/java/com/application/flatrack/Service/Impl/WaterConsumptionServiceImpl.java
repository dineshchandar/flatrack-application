package com.application.flatrack.Service.Impl;

import com.application.flatrack.Model.WaterConsumptionRecord;
import com.application.flatrack.Repsository.BankStatementRepository;
import com.application.flatrack.Repsository.WaterConsumptionRepository;
import com.application.flatrack.Service.WaterConsumptionService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WaterConsumptionServiceImpl implements WaterConsumptionService {

    @Autowired
    WaterConsumptionRepository waterConsumptionRepository;

    @Override
    public void loadWaterConsumption(MultipartFile waterConsumptionFile) {
        try (InputStream is = waterConsumptionFile.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            List<WaterConsumptionRecord> waterConsumptionRecords = new ArrayList<>();

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            // Extract date headers from column 5 onwards (index 4)
            List<LocalDate> dateColumns = new ArrayList<>();

            for (int col = 4; col < headerRow.getLastCellNum()-1; col++) {
                Cell cell = headerRow.getCell(col);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String dateStr = cell.getStringCellValue().trim();
                    dateColumns.add(convertToDate(dateStr));
                }
            }
            // Process each data row
            String savedApartment = "";
            String savedOwner = "";
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null ) continue;
                String apartment;
                String owner;
                if(getCellValue(row.getCell(0)) == null) {
                    apartment = savedApartment;
                    owner = savedOwner;
                }
                else {
                    apartment = getCellValue(row.getCell(0));
                    savedApartment = getCellValue(row.getCell(0));
                    owner = getCellValue(row.getCell(1));
                    savedOwner = getCellValue(row.getCell(1));
                }

                if(apartment.equalsIgnoreCase("Total"))
                    break;

                String location = getCellValue(row.getCell(2));
                String meterNo = getCellValue(row.getCell(3));

                for (int i = 0; i < dateColumns.size(); i++) {
                    Cell readingCell = row.getCell(4 + i);
                    if (readingCell == null || readingCell.getCellType() != CellType.NUMERIC) continue;

                    double reading = readingCell.getNumericCellValue();
                    LocalDate readingDate = dateColumns.get(i);

                    WaterConsumptionRecord waterConsumptionRecord = new WaterConsumptionRecord();
                    waterConsumptionRecord.setApartment(apartment.replaceAll("[^0-9]", ""));
                    waterConsumptionRecord.setOwner(owner);
                    waterConsumptionRecord.setLocation(location);
                    waterConsumptionRecord.setMeterNo(meterNo);
                    waterConsumptionRecord.setReadingDate(readingDate);
                    waterConsumptionRecord.setReadingValue(BigDecimal.valueOf(reading));
                    waterConsumptionRecords.add(waterConsumptionRecord);
                }
            }
            waterConsumptionRepository.saveAll(waterConsumptionRecords);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file: " + e.getMessage());
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();

            case NUMERIC -> {
                // Check if it’s actually an integer (like Meter No)
                double value = cell.getNumericCellValue();
                if (value == Math.floor(value)) {
                    // No decimal places, treat as long
                    yield String.valueOf((long) value);
                } else {
                    // Has decimal places
                    yield String.valueOf(value);
                }
            }

            case FORMULA -> getCellValueFromFormula(cell);
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> null;
        };
    }

    private String getCellValueFromFormula(Cell cell) {
        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        CellValue cellValue = evaluator.evaluate(cell);
        return switch (cellValue.getCellType()) {
            case STRING -> cellValue.getStringValue();
            case NUMERIC -> {
                double value = cellValue.getNumberValue();
                if (value == Math.floor(value)) {
                    yield String.valueOf((long) value);
                } else {
                    yield String.valueOf(value);
                }
            }
            case BOOLEAN -> String.valueOf(cellValue.getBooleanValue());
            default -> "";
        };
    }


    public LocalDate convertToDate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Invalid date string: " + input);
        }

        // Ensure single space and title case (e.g., "1 Sep")
        String cleaned = input.trim().replaceAll("\\s+", " ");

        // Append current year
        int currentYear = LocalDate.now().getYear();
        String dateStr = cleaned + " " + currentYear;

        // Define formatter for "d MMM yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);

        return LocalDate.parse(dateStr, formatter);
    }
}

