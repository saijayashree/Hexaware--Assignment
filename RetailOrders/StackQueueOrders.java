
package RetailOrders;

import java.io.*;
import java.util.*;

public class StackQueueOrders {

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

    // Custom Stack - LIFO
    static class MyStack {

        double[] stack;
        int top = -1;

        MyStack(int size) {
            stack = new double[size];
        }

        void push(double value) {

            if (top == stack.length - 1) {
                System.out.println("Stack Overflow");
                return;
            }

            stack[++top] = value;
        }

        double pop() {

            if (top == -1) {
                System.out.println("Stack Underflow");
                return -1;
            }

            return stack[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    // Custom Circular Queue - FIFO
    static class MyQueue {

        Order[] queue;
        int front = 0;
        int rear = -1;
        int count = 0;

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

        File file = new File("orders.csv");

        try (BufferedReader br =
                 new BufferedReader(new FileReader(file))) {

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

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Orders: " + orders.size());

        if (orders.isEmpty()) {
            System.out.println("No orders available. Check your CSV file.");
            return;
        }

        // =================================
        // STACK - Undo Last Order Edit
        // =================================

        System.out.println("\n===== CUSTOM STACK =====");

        Order firstOrder = orders.get(0);

        MyStack customStack = new MyStack(10);

        double oldAmount = firstOrder.amount;

        System.out.println("Original Amount: " + oldAmount);

        // Save old amount before editing
        customStack.push(oldAmount);

        // Edit order amount
        firstOrder.amount = firstOrder.amount + 500;

        System.out.println("After Editing: " + firstOrder.amount);

        // Undo operation
        if (!customStack.isEmpty()) {
            firstOrder.amount = customStack.pop();
        }

        System.out.println("After Undo: " + firstOrder.amount);

        // =================================
        // BUILT-IN STACK
        // =================================

        System.out.println("\n===== BUILT-IN STACK =====");

        ArrayDeque<Double> builtInStack = new ArrayDeque<>();

        builtInStack.push(firstOrder.amount);

        firstOrder.amount += 500;

        System.out.println("Built-in Stack Edited: " +
                           firstOrder.amount);

        firstOrder.amount = builtInStack.pop();

        System.out.println("Built-in Stack Undo: " +
                           firstOrder.amount);

        // =================================
        // QUEUE - Dispatch Orders
        // =================================

        System.out.println("\n===== CUSTOM QUEUE DISPATCH =====");

        MyQueue customQueue = new MyQueue(orders.size());

        // Insert all orders into queue
        for (Order order : orders) {
            customQueue.enqueue(order);
        }

        // Dispatch first five orders
        for (int i = 0; i < 5 && !customQueue.isEmpty(); i++) {

            Order order = customQueue.dequeue();

            System.out.println("Dispatched Order: " +
                               order.orderId);
        }

        // =================================
        // BUILT-IN QUEUE
        // =================================

        System.out.println("\n===== BUILT-IN QUEUE DISPATCH =====");

        Queue<Order> builtInQueue = new ArrayDeque<>(orders);

        // Dispatch first five orders
        for (int i = 0; i < 5 && !builtInQueue.isEmpty(); i++) {

            Order order = builtInQueue.poll();

            System.out.println("Dispatched Order: " +
                               order.orderId);
        }

        // =================================
        // TIME COMPLEXITY
        // =================================

        System.out.println("\n===== TIME COMPLEXITY =====");

        System.out.println("Stack Push: O(1)");
        System.out.println("Stack Pop: O(1)");
        System.out.println("Queue Enqueue: O(1)");
        System.out.println("Queue Dequeue: O(1)");
    }
}
