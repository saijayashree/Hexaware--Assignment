
package RetailOrders;

import java.io.*;
import java.util.*;

public class DuplicateOrders {

    static class Order {
        int orderId;
        int customerId;
        double amount;
        String createdAt;

        Order(int id, int cid, double amt, String date) {
            orderId = id;
            customerId = cid;
            amount = amt;
            createdAt = date;
        }
    }

    // Key used to identify duplicate orders
    static class OrderKey {

        int customerId;
        double amount;
        String minute;

        OrderKey(Order o) {
            customerId = o.customerId;
            amount = o.amount;

            // Compare date and time up to the minute
            minute = o.createdAt.substring(0, 16);
        }

        @Override
        public boolean equals(Object obj) {

            if (!(obj instanceof OrderKey)) {
                return false;
            }

            OrderKey other = (OrderKey) obj;

            return customerId == other.customerId
                && Double.compare(amount, other.amount) == 0
                && minute.equals(other.minute);
        }

        @Override
        public int hashCode() {

            return Objects.hash(customerId, amount, minute);
        }
    }

    // Load all orders from CSV
    static List<Order> loadOrders() {

        List<Order> orders = new ArrayList<>();

        File file = new File("orders.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            // Skip CSV header
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");

                orders.add(new Order(
                    Integer.parseInt(d[0].trim()),
                    Integer.parseInt(d[1].trim()),
                    Double.parseDouble(d[2].trim()),
                    d[3].trim()
                ));
            }

            System.out.println("CSV file loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("Error: orders.csv not found!");

        } catch (Exception e) {

            System.out.println("CSV Error: " + e.getMessage());
        }

        return orders;
    }

    // Detect duplicate orders using HashMap
    static void findDuplicates(List<Order> orders) {

        Map<OrderKey, Order> map = new HashMap<>();

        int duplicateCount = 0;

        for (Order order : orders) {

            OrderKey key = new OrderKey(order);

            if (map.containsKey(key)) {

                Order original = map.get(key);

                System.out.println(
                    "Duplicate Found: " +
                    "Original ID = " + original.orderId +
                    ", Duplicate ID = " + order.orderId
                );

                duplicateCount++;

            } else {

                map.put(key, order);
            }
        }

        System.out.println("\nTotal Orders: " + orders.size());
        System.out.println("Unique Orders: " + map.size());
        System.out.println("Duplicate Orders: " + duplicateCount);
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available. Check your CSV file.");
            return;
        }

        System.out.println("Total Records Loaded: " + orders.size());

        System.out.println("\nChecking Duplicate Orders...");

        long start = System.nanoTime();

        findDuplicates(orders);

        long end = System.nanoTime();

        System.out.println(
            "\nExecution Time: " + (end - start) + " ns"
        );

        System.out.println("Time Complexity: O(n) average case");
    }
}
