package code.leetcode.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] combinations = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int n = 0; n < coins.length; n++) {
                if (i >= coins[n]) {
                    int base = i - coins[n];
                    if (dp[base] >= 0) {
                        int num = dp[base] + 1;
                        if (num < minCoins) {
                            minCoins = num;
                            combinations[i] = coins[n];
                        }
                    }
                }
            }
            dp[i] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        }

        // Print combinations o coins
        List<String> combo = new ArrayList<>();
        while (amount > 0) {
            combo.add(String.valueOf(combinations[amount]));
            amount -= combinations[amount];
        }
        System.out.println("[" + String.join(",", combo) + "]");

        return combo.size();
    }

    @Test
    void test1() {
        assertEquals(3, coinChange(new int[] {1, 2, 5}, 11));
    }
}
