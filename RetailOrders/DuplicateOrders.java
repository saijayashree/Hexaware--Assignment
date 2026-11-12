
package RetailOrders;


import java.util.*;
import java.io.*;

public class DuplicateOrders {

    static class Order {
        int id, customerId;
        double amount;
        String date;

        Order(int id, int cid, double amt, String date) {
            this.id = id;
            customerId = cid;
            amount = amt;
            this.date = date;
        }
    }

    public static void main(String[] args) {

        List<Order> orders = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("orders.csv"));

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

            br.close();

        } catch (Exception e) {
            System.out.println("CSV File Error");
            return;
        }

        Map<String, Integer> map = new HashMap<>();

        int count = 0;

        for (Order o : orders) {

            String key = o.customerId + "-" + o.amount + "-" + o.date.substring(0, 16);

            if (map.containsKey(key)) {
                System.out.println("Duplicate Found: " + map.get(key) + " and " + o.id);
                count++;
            }
            else {
                map.put(key, o.id);
            }
        }

        System.out.println("\nTotal Orders: " + orders.size());
        System.out.println("Unique Orders: " + map.size());
        System.out.println("Duplicate Orders: " + count);
    }
}