SELECT
    h.hub_id,
    h.hub_name,
    (
        SELECT MAX(s1.shipping_cost)
        FROM Shipments s1
        WHERE s1.hub_id = h.hub_id
        AND s1.shipping_cost < (
            SELECT MAX(s2.shipping_cost)
            FROM Shipments s2
            WHERE s2.hub_id = h.hub_id
        )
    ) AS second_highest_shipping_cost
FROM Hubs h;