package com.application.flatrack.Controller;

import com.application.flatrack.Models.Resident.ResidentResponse;
import com.application.flatrack.Models.Resident.Resident;
import com.application.flatrack.Service.ResidentServices.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/web/v1")
public class ResidentController {

    List<Resident> residentList = new ArrayList<>();

    @Autowired
    ResidentService residentService;

    @GetMapping("/resident/{id}")
    public ResidentResponse getResident(@PathVariable int id) {
       System.out.println("Entered ID is " + id);
       return residentService.getResidentInfo(id);
    }

    @PostMapping("/addResident")
    public String addResident(@RequestBody Resident resident) {
        return residentService.addResident(resident);
    }
}
