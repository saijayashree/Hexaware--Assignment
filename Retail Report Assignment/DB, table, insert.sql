
CREATE DATABASE retail_sales;

USE retail_sales;

-- 1. Customers table
CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    city VARCHAR(50),
    email VARCHAR(100)
);

-- 2. Products table
CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2)
);

-- 3. Orders table
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
);

-- 4. Order items table
CREATE TABLE order_items (
    item_id INT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    unit_price DECIMAL(10,2),
    FOREIGN KEY (order_id)
        REFERENCES orders(order_id),
    FOREIGN KEY (product_id)
        REFERENCES products(product_id)
);

-- 5. Payments table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY,
    order_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    payment_method VARCHAR(50),
    FOREIGN KEY (order_id)
        REFERENCES orders(order_id)
);



INSERT INTO products VALUES
(101, 'Laptop', 'Electronics', 50000),
(102, 'Mobile', 'Electronics', 20000),
(103, 'Headphones', 'Accessories', 2000),
(104, 'Shoes', 'Fashion', 3000),
(105, 'Watch', 'Fashion', 5000),
(106, 'Keyboard', 'Accessories', 1500);



INSERT INTO orders VALUES
(1001, 1, '2026-01-05'),
(1002, 1, '2026-01-15'),
(1003, 1, '2026-02-10'),
(1004, 1, '2026-03-05'),
(1005, 2, '2026-01-20'),
(1006, 2, '2026-02-15'),
(1007, 3, '2026-02-20'),
(1008, 4, '2026-03-10'),
(1009, 5, '2026-03-15');



INSERT INTO order_items VALUES
(1, 1001, 101, 1, 50000),
(2, 1001, 103, 2, 2000),
(3, 1002, 102, 1, 20000),
(4, 1002, 104, 2, 3000),
(5, 1003, 101, 1, 50000),
(6, 1003, 105, 1, 5000),
(7, 1004, 102, 2, 20000),
(8, 1005, 103, 3, 2000),
(9, 1005, 106, 2, 1500),
(10, 1006, 104, 1, 3000),
(11, 1007, 105, 2, 5000),
(12, 1008, 101, 1, 50000),
(13, 1009, 106, 4, 1500);



INSERT INTO payments VALUES
(1, 1001, '2026-01-05', 54000, 'UPI'),
(2, 1002, '2026-01-15', 26000, 'Card'),
(3, 1003, '2026-02-10', 55000, 'UPI'),
(4, 1005, '2026-01-20', 9000, 'Cash'),
(5, 1006, '2026-02-15', 3000, 'Card'),
(6, 1007, '2026-02-20', 10000, 'UPI'),
(7, 1008, '2026-03-10', 50000, 'Card');


INSERT INTO orders VALUES
(1001, 1, '2026-01-05'),
(1002, 1, '2026-01-15'),
(1003, 1, '2026-02-10'),
(1004, 1, '2026-03-05'),
(1005, 2, '2026-01-20'),
(1006, 2, '2026-02-15'),
(1007, 3, '2026-02-20'),
(1008, 4, '2026-03-10'),
(1009, 5, '2026-03-15');



INSERT INTO customers VALUES
(1, 'Arun', 'Chennai', 'arun@gmail.com'),
(2, 'Priya', 'Bangalore', 'priya@gmail.com'),
(3, 'Rahul', 'Mumbai', 'rahul@gmail.com'),
(4, 'Sneha', 'Chennai', 'sneha@gmail.com'),
(5, 'Kiran', 'Hyderabad', 'kiran@gmail.com'),
(6, 'Divya', 'Pune', 'divya@gmail.com');
