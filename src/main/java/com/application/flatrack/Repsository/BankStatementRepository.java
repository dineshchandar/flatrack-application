package com.application.flatrack.Repsository;


import com.application.flatrack.Model.BankStatementRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<BankStatementRecord, Long> {
}
