CREATE TABLE bank_statement_master (
    txn_no VARCHAR(20) PRIMARY KEY,
    txn_date DATE,
    description TEXT,
    branch_name VARCHAR(100),
    cheque_no VARCHAR(50),
    dr_amount DECIMAL(15,2),
    cr_amount DECIMAL(15,2),
    balance DECIMAL(15,2),
    balance_type ENUM('Dr', 'Cr'),
    kims_remarks TEXT
);
