
-- TASK 4: INSERT, UPDATE, DELETE AND OPERATORS

-- STEP 1: Insert a new customer
INSERT INTO Customers (customer_name, address, phone)
VALUES ('Vijay', 'Trichy', '9876543215');

SELECT * FROM Customers;


-- STEP 2: Update customer details
UPDATE Customers
SET address = 'Chennai'
WHERE customer_id = 1;

SELECT * FROM Customers;


-- STEP 3: Delete a customer
-- Delete only the newly inserted customer
DELETE FROM Customers
WHERE customer_id = 6;

SELECT * FROM Customers;


-- STEP 4: AND operator
SELECT * FROM Customers
WHERE address = 'Chennai'
AND customer_name = 'Divya';


-- STEP 5: OR operator
SELECT * FROM Customers
WHERE address = 'Chennai'
OR address = 'Madurai';


-- STEP 6: IN operator
SELECT * FROM Customers
WHERE address IN ('Chennai', 'Coimbatore');


-- STEP 7: BETWEEN operator
SELECT * FROM Customers
WHERE customer_id BETWEEN 1 AND 3;


-- STEP 8: LIKE operator
SELECT * FROM Customers
WHERE customer_name LIKE 'A%';


-- STEP 9: Accounts opened in last quarter
-- With balance above 20000

SELECT *
FROM Accounts
WHERE opened_date BETWEEN '2026-07-01' AND '2026-09-30'
AND balance > 20000;
