
USE retail_sales;

-- Query 1: WHERE clause

SELECT *
FROM orders
WHERE order_date > '2026-02-01';

-- Query 2: HAVING clause

SELECT
    customer_id,
    COUNT(order_id) AS total_orders

FROM orders

GROUP BY customer_id

HAVING COUNT(order_id) > 2;

-- Query 3: WHERE and HAVING together

SELECT
    customer_id,
    COUNT(order_id) AS total_orders

FROM orders

WHERE order_date > '2026-02-01'

GROUP BY customer_id

HAVING COUNT(order_id) > 1;
