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

    static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        File file = new File("orders.csv");

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                br.readLine();

                while ((line = br.readLine()) != null && orders.size() < 10000) {
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
            for (int i = 0; i < 10000; i++) {
                orders.add(new Order(
                    100001 + i, 500 + i % 1000,
                    100 + (i * 37) % 10000,
                    "2026-11-10T10:30:00"
                ));
            }
        }

        return orders;
    }

    // Bubble Sort - O(n^2)
    static void bubbleSort(Order[] a) {
        int n = a.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (a[j].amount < a[j + 1].amount) {
                    Order temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }

            if (i < 3) {
                System.out.println("After Pass " + (i + 1) + ":");
                for (int k = 0; k < Math.min(5, n); k++) {
                    System.out.print(a[k].amount + " ");
                }
                System.out.println();
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

    static void display(Order[] a) {
        for (int i = 0; i < Math.min(5, a.length); i++) {
            System.out.println(
                "Order ID: " + a[i].orderId +
                " Amount: " + a[i].amount
            );
        }
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Records: " + orders.size());

        Order[] bubble = orders.toArray(new Order[0]);
        Order[] merge = orders.toArray(new Order[0]);
        Order[] builtIn = orders.toArray(new Order[0]);

        // Bubble Sort
        long start = System.nanoTime();

        bubbleSort(bubble);

        long end = System.nanoTime();

        System.out.println("\nBubble Sort Time: " + (end - start) + " ns");

        // Merge Sort
        start = System.nanoTime();

        mergeSort(merge, 0, merge.length - 1);

        end = System.nanoTime();

        System.out.println("Merge Sort Time: " + (end - start) + " ns");

        // Built-in Sort
        start = System.nanoTime();

        Arrays.sort(builtIn,
            (a, b) -> Double.compare(b.amount, a.amount));

        end = System.nanoTime();

        System.out.println("Built-in Sort Time: " + (end - start) + " ns");

        System.out.println("\nTop 5 Orders - Bubble Sort:");
        display(bubble);

        System.out.println("\nTop 5 Orders - Merge Sort:");
        display(merge);

        System.out.println("\nTop 5 Orders - Built-in Sort:");
        display(builtIn);
    }
}
