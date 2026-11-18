
-- TASK 3: DELETE, TRUNCATE AND DROP

-- STEP 1: Remove old table if it already exists
DROP TABLE IF EXISTS Test_Accounts;

-- STEP 2: Create table
CREATE TABLE Test_Accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

-- STEP 3: Insert sample records
INSERT INTO Test_Accounts (name)
VALUES
('Arun'),
('Priya'),
('Kumar');

-- Display all records
SELECT * FROM Test_Accounts;


-- STEP 4: DELETE a particular record
DELETE FROM Test_Accounts
WHERE id = 2;

SELECT * FROM Test_Accounts;


-- STEP 5: Demonstrate ROLLBACK
START TRANSACTION;

DELETE FROM Test_Accounts
WHERE id > 0;

SELECT * FROM Test_Accounts;

ROLLBACK;

-- Records will return after rollback
SELECT * FROM Test_Accounts;


-- STEP 6: TRUNCATE the table
TRUNCATE TABLE Test_Accounts;

SELECT * FROM Test_Accounts;


-- STEP 7: DROP the table
DROP TABLE Test_Accounts;

-- Check whether table exists
SHOW TABLES;
