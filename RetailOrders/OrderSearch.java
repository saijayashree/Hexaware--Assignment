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

    static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();

        File file = new File("orders.csv");

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
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
            } catch (Exception e) {
                System.out.println("Error reading CSV: " + e.getMessage());
            }
        } else {
            for (int i = 0; i < 50000; i++) {
                orders.add(new Order(
                    100001 + i,
                    500 + i % 1000,
                    100 + i % 5000,
                    "2026-11-10T10:30:00"
                ));
            }
        }

        return orders;
    }

    static Order linearSearch(List<Order> orders, int id) {
        for (Order order : orders) {
            if (order.orderId == id) {
                return order;
            }
        }
        return null;
    }

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

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Order ID to search: ");
        int id = sc.nextInt();

        // Linear Search
        long start = System.nanoTime();

        Order result1 = linearSearch(orders, id);

        long end = System.nanoTime();

        System.out.println("\nLinear Search:");

        if (result1 != null) {
            System.out.println("Order Found: " + result1.orderId);
            System.out.println("Amount: " + result1.amount);
        } else {
            System.out.println("Order Not Found");
        }

        System.out.println("Time: " + (end - start) + " ns");

        // Sort before Binary Search
        orders.sort(Comparator.comparingInt(o -> o.orderId));

        start = System.nanoTime();

        Order result2 = binarySearch(orders, id);

        end = System.nanoTime();

        System.out.println("\nBinary Search:");

        if (result2 != null) {
            System.out.println("Order Found: " + result2.orderId);
            System.out.println("Amount: " + result2.amount);
        } else {
            System.out.println("Order Not Found");
        }

        System.out.println("Time: " + (end - start) + " ns");
        sc.close();
    }
    
}
