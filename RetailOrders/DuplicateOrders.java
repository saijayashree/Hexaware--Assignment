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

    static class OrderKey {
        int customerId;
        double amount;
        String minute;

        OrderKey(Order o) {
            customerId = o.customerId;
            amount = o.amount;
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

    static List<Order> loadOrders() {

        List<Order> orders = new ArrayList<>();
        File file = new File("orders.csv");

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
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

            } catch (Exception e) {
                System.out.println("CSV Error: " + e.getMessage());
            }

        } else {
            for (int i = 0; i < 50000; i++) {

                int customer = 500 + i % 100;
                double amount = 100 + (i % 20) * 50;

                // Repeated minutes create sample duplicates
                int minute = i % 10;

                String date = String.format(
                    "2026-11-10T10:%02d:00", minute
                );

                orders.add(new Order(
                    100001 + i, customer, amount, date
                ));
            }
        }

        return orders;
    }

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
        System.out.println("Duplicate Orders: " + duplicateCount);
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Checking Duplicate Orders...");

        long start = System.nanoTime();

        findDuplicates(orders);

        long end = System.nanoTime();

        System.out.println(
            "Execution Time: " + (end - start) + " ns"
        );
    }
}
