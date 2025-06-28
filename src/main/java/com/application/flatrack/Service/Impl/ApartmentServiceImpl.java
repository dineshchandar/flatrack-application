package com.application.flatrack.Service.Impl;

import com.application.flatrack.Model.Apartment;
import com.application.flatrack.Repsository.ApartmentRepository;
import com.application.flatrack.Service.ApartmentService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public void loadApartmentInfo(MultipartFile file) {
        List<Apartment> apartments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord record : csvParser) {
                Apartment apartment = new Apartment();

                apartment.setFloor(record.get(0).trim());
                apartment.setFlatNo(record.get(1).trim());
                apartment.setOwnerName(record.get(2).trim());

                String occupiedBy = record.get(3).trim();
                apartment.setOccupiedBy(Apartment.OccupiedBy.valueOf(occupiedBy));

                String tenantName = record.get(4).trim();
                apartment.setTenantName(tenantName.equalsIgnoreCase("NA") ? null : tenantName);

                int area = Integer.parseInt(record.get(5).trim());
                apartment.setAreaInSqft(area);

                apartments.add(apartment);
            }
            apartmentRepository.saveAll(apartments);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
        }
    }
}
