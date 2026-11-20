
-- TASK 1: LOGISTICS SHIPMENT ANALYSIS

-- 1. Find shipments delayed by more than 2 days
-- Display shipment ID and carrier name

SELECT
    s.shipment_id,
    c.carrier_name,
    s.delay_days
FROM Shipments s
INNER JOIN Carriers c
ON s.carrier_id = c.carrier_id
WHERE s.delay_days > 2;


-- 2. Find hubs with no outbound shipments

SELECT
    h.hub_id,
    h.hub_name
FROM Hubs h
LEFT JOIN Shipments s
ON h.hub_id = s.hub_id
WHERE s.shipment_id IS NULL;


-- 3. Display employees with their supervisors

SELECT
    e.employee_name AS Employee,
    m.employee_name AS Supervisor
FROM Employees e
LEFT JOIN Employees m
ON e.supervisor_id = m.employee_id;


-- 4. Display customers and their shipment details

SELECT
    c.customer_name,
    s.shipment_id,
    s.shipping_cost
FROM Customers c
INNER JOIN Shipments s
ON c.customer_id = s.customer_id;


-- 5. Display all customers, including those
-- who have not made any shipments

SELECT
    c.customer_name,
    s.shipment_id
FROM Customers c
LEFT JOIN Shipments s
ON c.customer_id = s.customer_id;


-- 6. Display all carriers, including those
-- who have not handled any shipments

SELECT
    c.carrier_name,
    s.shipment_id
FROM Shipments s
RIGHT JOIN Carriers c
ON s.carrier_id = c.carrier_id;


-- 7. Display shipments with their hub details

SELECT
    s.shipment_id,
    h.hub_name,
    s.shipping_cost
FROM Shipments s
INNER JOIN Hubs h
ON s.hub_id = h.hub_id;


-- 8. Display shipments with their delivery events

SELECT
    s.shipment_id,
    d.event_date,
    d.event_status
FROM Shipments s
LEFT JOIN Delivery_Events d
ON s.shipment_id = d.shipment_id;


-- 9. Display carriers and their total shipment count

SELECT
    c.carrier_name,
    COUNT(s.shipment_id) AS total_shipments
FROM Carriers c
LEFT JOIN Shipments s
ON c.carrier_id = s.carrier_id
GROUP BY c.carrier_id, c.carrier_name;


-- 10. Display hubs and their total shipping cost

SELECT
    h.hub_name,
    SUM(s.shipping_cost) AS total_shipping_cost
FROM Hubs h
LEFT JOIN Shipments s
ON h.hub_id = s.hub_id
GROUP BY h.hub_id, h.hub_name;
