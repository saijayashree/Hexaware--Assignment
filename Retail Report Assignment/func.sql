
USE retail_sales;

-- Query 1: UPPER()

SELECT
    customer_id,
    UPPER(customer_name) AS customer_name
FROM customers;

-- Query 2: CONCAT()

SELECT
    customer_id,
    CONCAT(customer_name, ' - ', city) AS customer_details
FROM customers;

-- Query 3: DATEDIFF()

SELECT
    o.order_id,
    o.order_date,
    p.payment_date,
    DATEDIFF(p.payment_date, o.order_date) AS days_difference

FROM orders o

JOIN payments p
ON o.order_id = p.order_id;

-- Query 4: DATE_FORMAT()

SELECT
    order_id,
    DATE_FORMAT(order_date, '%d-%m-%Y') AS formatted_date
FROM orders;

-- Query 5: ROUND()

SELECT
    product_name,
    price,
    ROUND(price / 3, 2) AS rounded_price
FROM products;
