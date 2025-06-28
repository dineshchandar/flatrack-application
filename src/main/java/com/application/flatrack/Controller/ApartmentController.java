package com.application.flatrack.Controller;

import com.application.flatrack.Service.Impl.ApartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/web/v1/apartment")
public class ApartmentController {

    @Autowired
    ApartmentServiceImpl apartmentService;

    @PostMapping("/load")
    public void loadApartmentInfo(@RequestParam("file") MultipartFile file) {
        apartmentService.loadApartmentInfo(file);
    }
}
