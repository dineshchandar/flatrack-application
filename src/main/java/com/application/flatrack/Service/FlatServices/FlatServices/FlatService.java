package com.application.flatrack.Service.FlatServices.FlatServices;

import com.application.flatrack.Models.Flat.FlatInfo;
import com.application.flatrack.Models.Flat.FlatResponse;
import com.application.flatrack.Models.Resident.Resident;
import com.application.flatrack.Models.Resident.ResidentResponse;

public interface FlatService {

    FlatResponse getFlatInfo(String flastNumber);
    String addFlat(FlatInfo flat);
}
