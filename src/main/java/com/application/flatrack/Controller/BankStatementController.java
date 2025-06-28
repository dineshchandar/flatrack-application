package com.application.flatrack.Controller;

import com.application.flatrack.Service.Impl.BankStatementServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.flatrack.Service.BankStatementService;

@RestController
@RequestMapping("/web/v1/bankstatement")
public class BankStatementController {

    @Autowired
    private BankStatementServiceImpl bankStatementService;

    @PostMapping("/load")
    public void loadBankStatement(@RequestParam("file") MultipartFile file) {
        bankStatementService.loadBankStatement(file);
    }
}
