
package RetailOrders;



import java.io.*;
import java.util.*;

 class StackQueueOrders {

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

    // Custom Stack
    static class MyStack {
        double[] stack = new double[10];
        int top = -1;

        void push(double value) {
            if (top == stack.length - 1) {
                System.out.println("Stack Full");
                return;
            }
            stack[++top] = value;
        }

        double pop() {
            if (top == -1) {
                System.out.println("Stack Empty");
                return -1;
            }
            return stack[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    // Custom Queue
    static class MyQueue {
        Order[] queue;
        int front = 0, rear = -1, count = 0;

        MyQueue(int size) {
            queue = new Order[size];
        }

        void enqueue(Order order) {
            if (count == queue.length) {
                System.out.println("Queue Full");
                return;
            }

            rear = (rear + 1) % queue.length;
            queue[rear] = order;
            count++;
        }

        Order dequeue() {
            if (count == 0) {
                System.out.println("Queue Empty");
                return null;
            }

            Order order = queue[front];
            queue[front] = null;
            front = (front + 1) % queue.length;
            count--;

            return order;
        }

        boolean isEmpty() {
            return count == 0;
        }
    }

    // Load orders from CSV
    static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader br =
                 new BufferedReader(new FileReader("orders.csv"))) {

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                orders.add(new Order(
                    Integer.parseInt(d[0].trim()),
                    Integer.parseInt(d[1].trim()),
                    Double.parseDouble(d[2].trim()),
                    d[3].trim()
                ));
            }

            System.out.println("CSV Loaded Successfully!");

        } catch (Exception e) {
            System.out.println("CSV Error: " + e.getMessage());
        }

        return orders;
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Orders: " + orders.size());

        if (orders.isEmpty()) {
            System.out.println("No Orders Available");
            return;
        }

        // STACK - Undo Order Edit
        System.out.println("\n===== CUSTOM STACK =====");

        Order firstOrder = orders.get(0);
       MyStack stack = new MyStack();
        System.out.println("Original Amount: " + firstOrder.amount);

        stack.push(firstOrder.amount);

        firstOrder.amount += 500;

        System.out.println("After Editing: " + firstOrder.amount);

        if (!stack.isEmpty()) {
            firstOrder.amount = stack.pop();
        }

        System.out.println("After Undo: " + firstOrder.amount);

        // QUEUE - Dispatch Orders
        System.out.println("\n===== CUSTOM QUEUE =====");

        MyQueue queue = new MyQueue(orders.size());

        // Enqueue all orders
        for (Order order : orders) {
            queue.enqueue(order);
        }

        System.out.println("Orders added to Queue.");

        // Dequeue first five orders
        for (int i = 0; i < 5 && !queue.isEmpty(); i++) {
            Order order = queue.dequeue();
            System.out.println("Dispatched Order: " + order.orderId);
        }

       
    }
}
