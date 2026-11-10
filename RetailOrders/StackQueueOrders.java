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

    // Custom Stack
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

    // Custom Queue
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

    static List<Order> loadOrders() {

        List<Order> orders = new ArrayList<>();
        File file = new File("orders.csv");

        if (file.exists()) {

            try (BufferedReader br = new BufferedReader(
                    new FileReader(file))) {

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
                orders.add(new Order(
                    100001 + i,
                    500 + i % 1000,
                    1000 + i,
                    "2026-11-10T10:30:00"
                ));
            }
        }

        return orders;
    }

    public static void main(String[] args) {

        List<Order> orders = loadOrders();

        System.out.println("Total Orders: " + orders.size());

        // STACK - Undo Last Order Edit

        Order firstOrder = orders.get(0);

        MyStack customStack = new MyStack(10);

        double oldAmount = firstOrder.amount;

        System.out.println("\nOriginal Amount: " + oldAmount);

        // Save previous value before editing
        customStack.push(oldAmount);

        firstOrder.amount = firstOrder.amount + 500;

        System.out.println("After Editing: " + firstOrder.amount);

        // Undo operation
        if (!customStack.isEmpty()) {
            firstOrder.amount = customStack.pop();
        }

        System.out.println("After Undo: " + firstOrder.amount);

        // Built-in Stack equivalent
        ArrayDeque<Double> builtInStack = new ArrayDeque<>();

        builtInStack.push(firstOrder.amount);
        firstOrder.amount += 500;

        System.out.println("Built-in Stack Edited: " +
                           firstOrder.amount);

        firstOrder.amount = builtInStack.pop();

        System.out.println("Built-in Stack Undo: " +
                           firstOrder.amount);

        // QUEUE - Dispatch Orders

        System.out.println("\nCustom Queue Dispatch:");

        MyQueue customQueue = new MyQueue(orders.size());

        for (Order order : orders) {
            customQueue.enqueue(order);
        }

        for (int i = 0; i < 5; i++) {
            Order order = customQueue.dequeue();

            System.out.println("Dispatched Order: " +
                               order.orderId);
        }

        // Built-in Queue equivalent
        System.out.println("\nBuilt-in Queue Dispatch:");

        Queue<Order> builtInQueue = new ArrayDeque<>(orders);

        for (int i = 0; i < 5; i++) {
            Order order = builtInQueue.poll();

            System.out.println("Dispatched Order: " +
                               order.orderId);
        }
    }
}
