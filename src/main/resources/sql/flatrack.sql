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

-- CREATE TABLE apartment_master (
--     flat_no VARCHAR(10) PRIMARY KEY,
--     floor VARCHAR(50),
--     owner_name VARCHAR(100),
--     occupied_by ENUM('Owner', 'Tenant', 'None'),
--     tenant_name VARCHAR(100),
--     area_in_sqft INT
-- );

select * from apartment_master order by flat_no asc;


