
package RetailOrders;

import java.io.*;
import java.util.*;

public class OrderSearch {

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

    // Load orders from CSV file
    static List<Order> loadOrders() {

        List<Order> orders = new ArrayList<>();

        File file = new File("orders.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            // Skip CSV header
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                orders.add(new Order(
                    Integer.parseInt(data[0].trim()),
                    Integer.parseInt(data[1].trim()),
                    Double.parseDouble(data[2].trim()),
                    data[3].trim()
                ));
            }

            System.out.println("CSV file loaded successfully!");

        } catch (FileNotFoundException e) {

            System.out.println("Error: orders.csv not found!");
            
        } catch (Exception e) {

            System.out.println("Error reading CSV: " + e.getMessage());
        }

        return orders;
    }

    // Linear Search - O(n)
    static Order linearSearch(List<Order> orders, int id) {

        for (Order order : orders) {

            if (order.orderId == id) {
                return order;
            }
        }

        return null;
    }

    // Binary Search - O(log n)
    static Order binarySearch(List<Order> orders, int id) {

        int low = 0;
        int high = orders.size() - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (orders.get(mid).orderId == id) {

                return orders.get(mid);

            } else if (orders.get(mid).orderId < id) {

                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Orders: " + orders.size());

        if (orders.isEmpty()) {
            System.out.println("No orders available. Check your CSV file.");
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter Order ID to search: ");
            int id = sc.nextInt();

            // Linear Search
            long start = System.nanoTime();

            Order result1 = linearSearch(orders, id);

            long end = System.nanoTime();

            long linearTime = end - start;

            System.out.println("\n----- LINEAR SEARCH -----");

            if (result1 != null) {

                System.out.println("Order Found: " + result1.orderId);
                System.out.println("Customer ID: " + result1.customerId);
                System.out.println("Amount: " + result1.amount);
                System.out.println("Created At: " + result1.createdAt);

            } else {

                System.out.println("Order Not Found");
            }

            System.out.println("Execution Time: " + linearTime + " ns");

            // Sort orders before Binary Search
            orders.sort(Comparator.comparingInt(o -> o.orderId));

            // Binary Search
            start = System.nanoTime();

            Order result2 = binarySearch(orders, id);

            end = System.nanoTime();

            long binaryTime = end - start;

            System.out.println("\n----- BINARY SEARCH -----");

            if (result2 != null) {

                System.out.println("Order Found: " + result2.orderId);
                System.out.println("Customer ID: " + result2.customerId);
                System.out.println("Amount: " + result2.amount);
                System.out.println("Created At: " + result2.createdAt);

            } else {

                System.out.println("Order Not Found");
            }

            System.out.println("Execution Time: " + binaryTime + " ns");

            // Comparison
            System.out.println("\n----- PERFORMANCE COMPARISON -----");

            System.out.println("Linear Search: " + linearTime + " ns");
            System.out.println("Binary Search: " + binaryTime + " ns");

            System.out.println("\nTime Complexity:");
            System.out.println("Linear Search: O(n)");
            System.out.println("Binary Search: O(log n)");
        }
    }
}
