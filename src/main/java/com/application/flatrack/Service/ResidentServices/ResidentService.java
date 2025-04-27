package com.application.flatrack.Service.ResidentServices;

import com.application.flatrack.Models.Resident.Resident;
import com.application.flatrack.Models.Resident.ResidentResponse;

public interface ResidentService {

    ResidentResponse getResidentInfo(int id);
    String addResident(Resident resident);
}
