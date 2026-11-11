
package RetailOrders;

import java.io.*;
import java.util.*;

public class OrderSorting {

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

    // Bubble Sort - O(n^2)
    // Sorts by amount in descending order
    static void bubbleSort(Order[] a) {

        int n = a.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (a[j].amount < a[j + 1].amount) {

                    Order temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    swapped = true;
                }
            }

            if (i < 3) {

                System.out.println("After Pass " + (i + 1) + ":");

                for (int k = 0; k < Math.min(5, n); k++) {
                    System.out.print(a[k].amount + " ");
                }

                System.out.println();
            }

            if (!swapped) {
                break;
            }
        }
    }

    // Merge Sort - O(n log n)
    static void mergeSort(Order[] a, int left, int right) {

        if (left < right) {

            int mid = left + (right - left) / 2;

            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);

            merge(a, left, mid, right);
        }
    }

    static void merge(Order[] a, int left, int mid, int right) {

        Order[] temp = new Order[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            if (a[i].amount >= a[j].amount) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = 0; i < temp.length; i++) {
            a[left + i] = temp[i];
        }
    }

    // Display top 5 orders
    static void display(Order[] a) {

        for (int i = 0; i < Math.min(5, a.length); i++) {

            System.out.println(
                "Order ID: " + a[i].orderId +
                " | Customer ID: " + a[i].customerId +
                " | Amount: " + a[i].amount
            );
        }
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Records: " + orders.size());

        if (orders.isEmpty()) {
            System.out.println("No records available. Check your CSV file.");
            return;
        }

        // Use first 5000 records for Bubble Sort
        int bubbleSize = Math.min(5000, orders.size());

        Order[] bubble = orders.subList(0, bubbleSize)
                               .toArray(new Order[0]);

        // Use all records for Merge Sort and Built-in Sort
        Order[] merge = orders.toArray(new Order[0]);

        Order[] builtIn = orders.toArray(new Order[0]);

        // Bubble Sort
        System.out.println("\n----- BUBBLE SORT -----");
        System.out.println("Records processed: " + bubble.length);

        long start = System.nanoTime();

        bubbleSort(bubble);

        long end = System.nanoTime();

        long bubbleTime = end - start;

        System.out.println("Bubble Sort Time: " + bubbleTime + " ns");

        // Merge Sort
        System.out.println("\n----- MERGE SORT -----");
        System.out.println("Records processed: " + merge.length);

        start = System.nanoTime();

        mergeSort(merge, 0, merge.length - 1);

        end = System.nanoTime();

        long mergeTime = end - start;

        System.out.println("Merge Sort Time: " + mergeTime + " ns");

        // Built-in Sort
        System.out.println("\n----- BUILT-IN SORT -----");
        System.out.println("Records processed: " + builtIn.length);

        start = System.nanoTime();

        Arrays.sort(builtIn,
            (a, b) -> Double.compare(b.amount, a.amount));

        end = System.nanoTime();

        long builtInTime = end - start;

        System.out.println("Built-in Sort Time: " + builtInTime + " ns");

        // Display results
        System.out.println("\n----- TOP 5 ORDERS -----");

        System.out.println("\nBubble Sort:");
        display(bubble);

        System.out.println("\nMerge Sort:");
        display(merge);

        System.out.println("\nBuilt-in Sort:");
        display(builtIn);

        // Performance comparison
        System.out.println("\n----- PERFORMANCE COMPARISON -----");

        System.out.println("Bubble Sort: " + bubbleTime + " ns (5000 records)");
        System.out.println("Merge Sort: " + mergeTime + " ns (all records)");
        System.out.println("Built-in Sort: " + builtInTime + " ns (all records)");

        System.out.println("\nTime Complexities:");
        System.out.println("Bubble Sort: O(n^2)");
        System.out.println("Merge Sort: O(n log n)");
        System.out.println("Built-in Sort: O(n log n) for object arrays");
    }
}
