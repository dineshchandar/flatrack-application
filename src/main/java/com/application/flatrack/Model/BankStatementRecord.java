package com.application.flatrack.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "bank_statement_master")
public class BankStatementRecord {

    @Id
    @Column(name = "txn_no", length = 20, nullable = false)
    private String txnNo;

    @Column(name = "txn_date")
    private LocalDate txnDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "branch_name", length = 100)
    private String branchName;

    @Column(name = "cheque_no", length = 50)
    private String chequeNo;

    @Column(name = "dr_amount", precision = 15, scale = 2)
    private BigDecimal drAmount;

    @Column(name = "cr_amount", precision = 15, scale = 2)
    private BigDecimal crAmount;

    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "balance_type", columnDefinition = "ENUM('Dr', 'Cr')")
    private BalanceType balanceType;

    @Column(name = "kims_remarks", columnDefinition = "TEXT")
    private String kimsRemarks;

    // Getters and setters

    public String getTxnNo() {
        return txnNo;
    }

    public void setTxnNo(String txnNo) {
        this.txnNo = txnNo;
    }

    public LocalDate getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public BigDecimal getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(BigDecimal drAmount) {
        this.drAmount = drAmount;
    }

    public BigDecimal getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(BigDecimal crAmount) {
        this.crAmount = crAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public String getKimsRemarks() {
        return kimsRemarks;
    }

    public void setKimsRemarks(String kimsRemarks) {
        this.kimsRemarks = kimsRemarks;
    }

    // Enum for balance type
    public enum BalanceType {
        Dr, Cr
    }
}