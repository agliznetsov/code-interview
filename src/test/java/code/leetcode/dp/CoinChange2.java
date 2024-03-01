package code.leetcode.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CoinChange2 {
    public int change(int amount, int[] coins) {
        // dp array to store the number of ways to make change for each amount
        int[] dp = new int[amount + 1];

        // There is 1 way to make change for the amount zero, that is to choose no coins
        dp[0] = 1;

        // Iterate over each type of coin
        for (int coin : coins) {
            // Update the dp array for all amounts that can be reached with the current coin
            for (int currentAmount = coin; currentAmount <= amount; currentAmount++) {
                // The number of ways to make change for currentAmount includes the number of ways
                // to make change for (currentAmount - coin value)
                dp[currentAmount] += dp[currentAmount - coin];
            }
        }

        // Return the total number of ways to make change for the specified amount
        return dp[amount];
    }

    @Test
    void test1() {
        assertEquals(4, change(5, new int[] {1, 2, 5}));
    }
}
