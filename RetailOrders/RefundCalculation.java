
package RetailOrders;

import java.util.*;

public class RefundCalculation {

    // Greedy Method
    static int greedy(int[] coins, int amount) {
        int count = 0;

        System.out.print("\nGreedy Coins: ");

        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
                System.out.print(coins[i] + " ");
            }
        }

        if (amount != 0) {
            System.out.println("\nExact refund not possible");
            return -1;
        }

        return count;
    }

    // Dynamic Programming Method
    static int dpMethod(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] selected = new int[amount + 1];

        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    selected[i] = coin;
                }
            }
        }

        if (dp[amount] > amount) {
            System.out.println("\nExact refund not possible");
            return -1;
        }

        System.out.print("\nDP Coins: ");

        while (amount > 0) {
            System.out.print(selected[amount] + " ");
            amount -= selected[amount];
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] coins = {1, 3, 4};

        System.out.println("Available Coins: " + Arrays.toString(coins));
        System.out.print("Enter Refund Amount: ");

        int amount = sc.nextInt();

        if (amount < 0) {
            System.out.println("Invalid Amount");
            sc.close();
            return;
        }

        int greedyResult = greedy(coins, amount);
        System.out.println("\nGreedy Count: " + greedyResult);

        int dpResult = dpMethod(coins, amount);
        System.out.println("\nDP Count: " + dpResult);

        System.out.println("\n===== COMPARISON =====");

        if (greedyResult == dpResult) {
            System.out.println("Both give the same result.");
        } else if (dpResult != -1 &&
                   (greedyResult == -1 || dpResult < greedyResult)) {
            System.out.println("DP gives the minimum coins.");
        } else {
            System.out.println("Greedy gives a different result.");
        }

        System.out.println("\nGreedy: O(n + amount)");
        System.out.println("DP: O(amount * n)");

        sc.close();
    }
}
