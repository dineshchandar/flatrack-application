package com.application.flatrack.Service.FlatServices.FlatServices.Impl;

import com.application.flatrack.Models.Flat.FlatInfo;
import com.application.flatrack.Models.Flat.FlatResponse;
import com.application.flatrack.Models.Resident.Resident;
import com.application.flatrack.Models.Resident.ResidentResponse;
import com.application.flatrack.Repsository.FlatRepo;
import com.application.flatrack.Service.FlatServices.FlatServices.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlatServiceImpl implements FlatService {

    @Autowired
    FlatRepo flatRepo;

    @Override
    public FlatResponse getFlatInfo(String flatNumber) {
        Optional<FlatInfo> flatInfo = flatRepo.findById(flatNumber);
        if (flatInfo.isPresent()) {
            return new FlatResponse(flatInfo.get(), "Flat Info found");
        }
        return new FlatResponse(null, "Flat Info not found");
    }

    @Override
    public String addFlat(FlatInfo flat) {
        return "";
    }

    // Implement the methods defined in the ResidentService interface here


}
