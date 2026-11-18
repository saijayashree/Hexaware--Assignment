
-- TASK 5: ALTER TABLE OPERATIONS

-- Add KYC status to Customers table

ALTER TABLE Customers
ADD COLUMN kyc_status VARCHAR(20)
DEFAULT 'Pending';

-- View updated Customers table

SELECT * FROM Customers;

DESC Customers;


-- STEP 2: UPDATE KYC STATUS
-- Change customer 1 status to Verified

UPDATE Customers
SET kyc_status = 'Verified'
WHERE customer_id = 1;

-- View updated records

SELECT * FROM Customers;


-- STEP 3: RENAME TABLE
-- Rename Accounts to Bank_Accounts

RENAME TABLE Accounts TO Bank_Accounts;

-- View all tables

SHOW TABLES;

-- View renamed table

SELECT * FROM Bank_Accounts;


-- STEP 4: DROP CHECK CONSTRAINT
-- Remove account type restriction

ALTER TABLE Bank_Accounts
DROP CHECK chk_account_type;

-- Verify the table structure

SHOW CREATE TABLE Bank_Accounts;

-- Final verification

DESC Bank_Accounts;
