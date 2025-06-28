package com.application.flatrack.Service;

import org.springframework.web.multipart.MultipartFile;

public interface BankStatementService {
    void loadBankStatement(MultipartFile bankStatementFile);
}
