SELECT *
FROM Customers
WHERE customer_id IN (
    SELECT customer_id
    FROM Shipments
);

SELECT DISTINCT
    c.*
FROM Customers c
INNER JOIN Shipments s
ON c.customer_id = s.customer_id;

SELECT *
FROM Hubs
WHERE hub_id NOT IN (
    SELECT hub_id
    FROM Shipments
    WHERE hub_id IS NOT NULL
);

SELECT
    h.*
FROM Hubs h
LEFT JOIN Shipments s
ON h.hub_id = s.hub_id
WHERE s.shipment_id IS NULL;