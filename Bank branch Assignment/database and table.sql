CREATE DATABASE BankBranchDB;

USE BankBranchDB;
CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    phone VARCHAR(15) UNIQUE
);
CREATE TABLE Branches (
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_name VARCHAR(100) NOT NULL,
    branch_location VARCHAR(100) NOT NULL
);
CREATE TABLE RelationshipManagers (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    manager_name VARCHAR(100) NOT NULL,
    branch_id INT,
    FOREIGN KEY (branch_id) REFERENCES Branches(branch_id)
);

CREATE TABLE Accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    customer_id INT NOT NULL,
    account_type VARCHAR(20) NOT NULL,
    branch_id INT NOT NULL,
    manager_id INT,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    opened_date DATE NOT NULL,

    FOREIGN KEY (customer_id)
        REFERENCES Customers(customer_id),

    FOREIGN KEY (branch_id)
        REFERENCES Branches(branch_id),

    FOREIGN KEY (manager_id)
        REFERENCES RelationshipManagers(manager_id),

    CONSTRAINT chk_balance
        CHECK (balance >= 0),

    CONSTRAINT chk_account_type
        CHECK (account_type IN
        ('Savings', 'Current', 'Fixed Deposit'))
);


INSERT INTO Customers
(customer_name, address, phone)
VALUES
('Arun', 'Chennai', '9876543210'),
('Priya', 'Coimbatore', '9876543211'),
('Kumar', 'Madurai', '9876543212'),
('Divya', 'Chennai', '9876543213'),
('Rahul', 'Salem', '9876543214');

INSERT INTO Branches
(branch_name, branch_location)
VALUES
('Main Branch', 'Chennai'),
('City Branch', 'Coimbatore'),
('South Branch', 'Madurai');

INSERT INTO RelationshipManagers
(manager_name, branch_id)
VALUES
('Ramesh', 1),
('Suresh', 2),
('Anitha', 3);

INSERT INTO Accounts
(account_number, customer_id, account_type,
 branch_id, manager_id, balance, opened_date)
VALUES
('ACC1001', 1, 'Savings', 1, 1, 25000.00, '2026-08-10'),
('ACC1002', 2, 'Current', 2, 2, 50000.00, '2026-09-15'),
('ACC1003', 3, 'Fixed Deposit', 3, 3, 75000.00, '2026-10-05'),
('ACC1004', 4, 'Savings', 1, 1, 15000.00, '2026-10-20'),
('ACC1005', 5, 'Current', 3, 3, 40000.00, '2026-11-01');