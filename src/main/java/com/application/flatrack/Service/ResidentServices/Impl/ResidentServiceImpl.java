package com.application.flatrack.Service.ResidentServices.Impl;

import com.application.flatrack.Models.Resident.Resident;
import com.application.flatrack.Models.Resident.ResidentResponse;
import com.application.flatrack.Repsository.ResidentRepo;
import com.application.flatrack.Service.ResidentServices.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    ResidentRepo residentRepo;

    @Override
    public ResidentResponse getResidentInfo(int id) {
        Optional<Resident> resident = residentRepo.findById(id);
        if (resident.isPresent()) {
            return new ResidentResponse(resident.get(), "Resident Info found");
        }
        return new ResidentResponse(null, "Resident Info not found");
    }

    @Override
    public String addResident(Resident resident) {
        residentRepo.save(resident);
        return "Resident added successfully!";
    }

    // Implement the methods defined in the ResidentService interface here


}
