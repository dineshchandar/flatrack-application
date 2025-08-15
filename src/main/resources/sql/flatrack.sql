-- CREATE TABLE bank_statement_master (
--     txn_no VARCHAR(20) PRIMARY KEY,
--     txn_date DATE,
--     description TEXT,
--     branch_name VARCHAR(100),
--     cheque_no VARCHAR(50),
--     dr_amount DECIMAL(15,2),
--     cr_amount DECIMAL(15,2),
--     balance DECIMAL(15,2),
--     balance_type ENUM('Dr', 'Cr'),
--     kims_remarks TEXT
-- );

-- CREATE TABLE water_consumption_master (
-- id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     apartment VARCHAR(10),
--     owner VARCHAR(100),
--     location VARCHAR(100),
--     meter_no VARCHAR(50),
--     reading_date DATE,
--     reading_value DECIMAL(10,2)
-- );

-- delete from water_consumption_master ;

SELECT * FROM flatrackdb.water_consumption_master;

select apartment
, sum(reading_value) 
from water_consumption_master 
where location is null 
and year(reading_date) = 2025 
and month(reading_date) = 9
group by apartment 
order by apartment asc
; 

SELECT * FROM flatrackdb.bank_statement_master;

CREATE TABLE maintenance_record (
    flat_no VARCHAR(10),
    floor VARCHAR(50),
    owner_name VARCHAR(100),
    occupied_by ENUM('Owner', 'Tenant'),
    tenant_name VARCHAR(100),
    area_in_sqft INT,
    report_date DATE,
    maintenance_date DATE,
    
    INDEX idx_flat_report_maint (flat_no, report_date, maintenance_date)
);


CREATE TABLE owners (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flat_no VARCHAR(10) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    ownership_start_date DATE NOT NULL,
    ownership_end_date DATE DEFAULT NULL,  -- NULL means still owning
    residing_status ENUM('Owner', 'Tenant') NOT NULL,  -- Who is residing now
    current_resident_name VARCHAR(100) DEFAULT NULL,   -- Owner name or tenant name
    is_current_owner BOOLEAN NOT NULL DEFAULT TRUE,    -- TRUE if current, FALSE if previous

    INDEX idx_flat_no (flat_no),
    INDEX idx_ownership_period (ownership_start_date, ownership_end_date)
);



