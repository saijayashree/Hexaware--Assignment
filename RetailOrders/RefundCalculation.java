
package RetailOrders;

import java.util.*;

public class RefundCalculation {

    // Greedy Approach
    static int greedy(int[] coins, int amount) {

        int count = 0;

        System.out.println("\nGreedy Selected Coins:");

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

    // Dynamic Programming Approach
    static int dynamicProgramming(int[] coins, int amount) {

        int[] dp = new int[amount + 1];

        int[] selectedCoin = new int[amount + 1];

        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {

            for (int coin : coins) {

                if (coin <= i && dp[i - coin] + 1 < dp[i]) {

                    dp[i] = dp[i - coin] + 1;

                    selectedCoin[i] = coin;
                }
            }
        }

        if (dp[amount] > amount) {

            System.out.println("Exact refund not possible");

            return -1;
        }

        System.out.println("\nDP Selected Coins:");

        int remaining = amount;

        while (remaining > 0) {

            int coin = selectedCoin[remaining];

            System.out.print(coin + " ");

            remaining -= coin;
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            // Available coin denominations
            int[] coins = {1, 3, 4};

            System.out.println(
                "Available Denominations: " +
                Arrays.toString(coins)
            );

            System.out.print("Enter Refund Amount: ");

            int amount = sc.nextInt();

            if (amount < 0) {

                System.out.println("Invalid refund amount");

                return;
            }

            // Greedy Algorithm
            int greedyResult = greedy(coins, amount);

            System.out.println(
                "\nGreedy Coin Count: " + greedyResult
            );

            // Dynamic Programming
            int dpResult = dynamicProgramming(coins, amount);

            System.out.println(
                "\nDP Coin Count: " + dpResult
            );

            // Comparison
            System.out.println("\n===== COMPARISON =====");

            if (greedyResult == -1 && dpResult == -1) {

                System.out.println(
                    "Neither approach can provide an exact refund."
                );

            } else if (greedyResult == dpResult) {

                System.out.println(
                    "Both approaches give the same result."
                );

            } else if (dpResult != -1 &&
                       (greedyResult == -1 || dpResult < greedyResult)) {

                System.out.println(
                    "Dynamic Programming gives the optimal result."
                );

            } else {

                System.out.println(
                    "Greedy gives a different result from DP."
                );
            }

            System.out.println("\nTime Complexities:");
            System.out.println("Greedy: O(n) for fixed sorted denominations");
            System.out.println("Dynamic Programming: O(amount * n)");
        }
    }
}
