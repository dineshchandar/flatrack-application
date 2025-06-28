package com.application.flatrack.Service.Impl;

import com.application.flatrack.Model.BankStatementRecord;
import com.application.flatrack.Repsository.BankStatementRepository;
import com.application.flatrack.Service.BankStatementService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BankStatementServiceImpl  implements BankStatementService {

    @Autowired
    BankStatementRepository bankStatementRepository;

    @Override
    public void loadBankStatement(MultipartFile bankStatementFile) {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(bankStatementFile.getInputStream(), StandardCharsets.UTF_8))
        ) {

            // Step 1: Skip first 16 rows
            for (int i = 0; i < 16; i++) {
                reader.readLine(); // discard lines
            }

            // Step 2: Read header line (16th row)
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new RuntimeException("Header row is missing in the file.");
            }

            // Split header and filter only non-empty column names
            String[] rawHeaderArray = headerLine.split(",", -1);
            List<Integer> validIndices = new ArrayList<>();
            List<String> cleanedHeaders = new ArrayList<>();

            for (int i = 0; i < rawHeaderArray.length; i++) {
                String col = rawHeaderArray[i].trim();
                if (!col.isEmpty()) {
                    cleanedHeaders.add(col);
                    validIndices.add(i);
                    System.out.println("i : " + i + " , " + col);
                }
            }

            // Step 3: Parse remaining lines
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withSkipHeaderRecord()
                    .withTrim()
                    .withIgnoreHeaderCase()
                    .withHeader(cleanedHeaders.toArray(new String[0]));

            CSVParser csvParser = new CSVParser(reader, csvFormat);

            for (CSVRecord record : csvParser) {

                if(record.get(3).isEmpty()){
                    break;
                }

                BankStatementRecord bankStatementRecord = new BankStatementRecord();

                String txnNo = record.get(1);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate txnDate = LocalDate.parse(record.get(3).trim(), formatter);

                String description = record.get(5);
                String branchName = record.get(7);
                String chequeNo = record.get(8);
                BigDecimal drAmount = record.get(9) == null || record.get(9).isEmpty() ? BigDecimal.valueOf(0) :  BigDecimal.valueOf(Double.parseDouble(record.get(9).replace(",","")));
                BigDecimal crAmount = record.get(10) == null || record.get(10).isEmpty() ? BigDecimal.valueOf(0) :  BigDecimal.valueOf(Double.parseDouble(record.get(10).replace(",","")));
                BigDecimal balance = record.get(11) == null || record.get(11).isEmpty() ? BigDecimal.valueOf(0) :  BigDecimal.valueOf(Double.parseDouble(record.get(11).replace(",","").replace("Cr.","").trim()));
                String kimsRemarks = record.get(13);

                bankStatementRecord.setTxnNo(txnNo);
                bankStatementRecord.setTxnDate(txnDate);
                bankStatementRecord.setDescription(description);
                bankStatementRecord.setBranchName(branchName);
                bankStatementRecord.setChequeNo(chequeNo);
                bankStatementRecord.setDrAmount(drAmount);
                bankStatementRecord.setCrAmount(crAmount);
                bankStatementRecord.setBalance(balance);
                bankStatementRecord.setKimsRemarks(kimsRemarks);

                bankStatementRepository.save(bankStatementRecord);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
        }

        System.out.println("bank statement loaded from service");
    }
}
