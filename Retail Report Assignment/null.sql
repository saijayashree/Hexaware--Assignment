
USE retail_sales;

-- Query 1: COUNT with NULL


SELECT
    COUNT(*) AS total_orders,
    COUNT(p.amount) AS paid_orders
FROM orders o
LEFT JOIN payments p
ON o.order_id = p.order_id;


-- Query 2: SUM with NULL


SELECT
    SUM(amount) AS total_payment
FROM payments;


-- Query 3: AVG with NULL


SELECT
    AVG(amount) AS average_payment
FROM payments;

-- Query 4: COALESCE with NULL


SELECT
    o.order_id,
    p.amount,
    COALESCE(p.amount, 0) AS updated_amount
FROM orders o
LEFT JOIN payments p
ON o.order_id = p.order_id;

