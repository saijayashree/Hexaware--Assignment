SELECT
    customer_id,
    customer_name
FROM Customers
WHERE customer_id IN (
    SELECT customer_id
    FROM Shipments
);

SELECT
    c.customer_id,
    c.customer_name
FROM Customers c
WHERE EXISTS (
    SELECT 1
    FROM Shipments s
    WHERE s.customer_id = c.customer_id
);

SELECT
    c.carrier_id,
    c.carrier_name,
    AVG(s.delay_days) AS average_delay
FROM Carriers c
INNER JOIN Shipments s
ON c.carrier_id = s.carrier_id
GROUP BY c.carrier_id, c.carrier_name
HAVING AVG(s.delay_days) > ANY (
    SELECT AVG(delay_days)
    FROM Shipments
    GROUP BY hub_id
);

SELECT
    c.carrier_id,
    c.carrier_name,
    AVG(s.delay_days) AS average_delay
FROM Carriers c
INNER JOIN Shipments s
ON c.carrier_id = s.carrier_id
GROUP BY c.carrier_id, c.carrier_name
HAVING AVG(s.delay_days) > ALL (
    SELECT AVG(delay_days)
    FROM Shipments
    GROUP BY hub_id
);

SELECT
    c.customer_id,
    c.customer_name
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Hubs h
    WHERE NOT EXISTS (
        SELECT 1
        FROM Shipments s
        WHERE s.customer_id = c.customer_id
        AND s.hub_id = h.hub_id
    )
);