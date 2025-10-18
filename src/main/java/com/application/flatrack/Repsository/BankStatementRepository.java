package com.application.flatrack.Repsository;


import com.application.flatrack.Model.Dbo.BankStatementRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<BankStatementRecord, Long> {
}
