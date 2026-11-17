
USE retail_sales;

-- Query 1: Top 5 Products by Revenue

SELECT
    p.product_name,
    SUM(oi.quantity * oi.unit_price) AS total_revenue
FROM products p
JOIN order_items oi
ON p.product_id = oi.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_revenue DESC
LIMIT 5;


-- Query 2: Month-wise Sales Report

SELECT
    DATE_FORMAT(o.order_date, '%M %Y') AS sales_month,
    SUM(oi.quantity * oi.unit_price) AS total_sales
FROM orders o
JOIN order_items oi
ON o.order_id = oi.order_id
GROUP BY sales_month
ORDER BY MIN(o.order_date);


-- Query 3: Customers with More Than Three Orders

SELECT
    c.customer_id,
    c.customer_name,
    COUNT(o.order_id) AS total_orders
FROM customers c
JOIN orders o
ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.customer_name
HAVING COUNT(o.order_id) > 3;


-- Query 4: Average Basket Value Per City

SELECT
    city,
    ROUND(AVG(order_total), 2) AS average_basket_value
FROM (
    SELECT
        c.city,
        o.order_id,
        SUM(oi.quantity * oi.unit_price) AS order_total
    FROM customers c
    JOIN orders o
    ON c.customer_id = o.customer_id
    JOIN order_items oi
    ON o.order_id = oi.order_id
    GROUP BY c.city, o.order_id
) AS order_summary
GROUP BY city;


-- Query 5: Orders Without Payment

SELECT
    o.order_id,
    o.customer_id,
    o.order_date
FROM orders o
LEFT JOIN payments p
ON o.order_id = p.order_id
WHERE p.payment_id IS NULL;


-- Query 6: Customers with No Orders

SELECT
    c.customer_id,
    c.customer_name,
    c.city,
    c.email
FROM customers c
LEFT JOIN orders o
ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;


-- Query 7: Product Category Revenue Share

SELECT
    p.category,
    SUM(oi.quantity * oi.unit_price) AS category_revenue,
    ROUND(
        SUM(oi.quantity * oi.unit_price) * 100.0 /
        (SELECT SUM(quantity * unit_price)
         FROM order_items), 2
    ) AS revenue_percentage
FROM products p
JOIN order_items oi
ON p.product_id = oi.product_id
GROUP BY p.category;


-- Query 8: Day with the Highest Sales

SELECT
    o.order_date,
    SUM(oi.quantity * oi.unit_price) AS total_sales
FROM orders o
JOIN order_items oi
ON o.order_id = oi.order_id
GROUP BY o.order_date
ORDER BY total_sales DESC
LIMIT 1;
