package code.techiedelight.medium;
/*

Given a rod of length `n` and a list of rod prices of length `i`, where `1 <= i <= n`, find the optimal way to cut the rod into smaller rods to maximize profit.

Input:

n = 4 									(Rod length)
price = [1, 5, 8, 9, 10, 17, 17, 20]	(Rod of length `i` has a cost price[i-1])

Output: 10
Explanation: Cut the rod into two pieces of length 2 each to gain revenue of 5 + 5 = 10

Cut		  		Profit

4				9
1, 3			(1 + 8) = 9
2, 2			(5 + 5) = 10	  <-- optimal way
3, 1			(8 + 1) = 9
1, 1, 2			(1 + 1 + 5) = 7
1, 2, 1			(1 + 5 + 1) = 7
2, 1, 1			(5 + 1 + 1) = 7
1, 1, 1, 1		(1 + 1 + 1 + 1) = 4

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class RodCutting {

    // Dynamic programing Top-down approach (Memoization)
    private static class Solver {
        final int[] price;
        final Map<Integer,Integer> profits = new HashMap<>();

        private Solver(int[] price) {
            this.price = price;
        }

        public int findMaxProfit(int length) {
            Integer maxProfit = profits.get(length);
            if (maxProfit == null) {
                maxProfit = price[length - 1];
                for (int i = 1; i < length; i++) {
                    int profit = findMaxProfit(i) + findMaxProfit(length - i);
                    maxProfit = Math.max(profit, maxProfit);
                }
                profits.put(length, maxProfit);
            }
            return maxProfit;
        }
    }

    // Dynamic programing Bottom-up approach (Tabulation)
    // Function to find the best way to cut a rod of length `n`
    // where the rod of length `i` has a cost `price[i-1]`
    public static int findMaxProfit(int[] price, int n) {
        if (n <= 0) {
            return 0;
        }

        // `[i]` stores the maximum profit achieved from a rod of length `i`
        int[] maxProfits = new int[n + 1];

        // consider a rod of length `i`
        for (int i = 1; i <= n; i++) {
            // divide the rod of length `i` into two rods of length `j`
            // and `i-j` each and take maximum
            for (int j = 1; j <= i; j++) {
                maxProfits[i] = Integer.max(maxProfits[i], price[j - 1] + maxProfits[i - j]);
            }
        }

        return maxProfits[n];
    }

    @Test
    void test() {
        assertEquals(10, findMaxProfit(new int[] {1, 5, 8, 9, 10, 17, 17, 20}, 4));
    }

    @Test
    void benchmark1() {
        int[] prices = getPrices();
        new Solver(prices).findMaxProfit(prices.length);
    }

    @Test
    void benchmark2() {
        int[] prices = getPrices();
        findMaxProfit(prices, prices.length);
    }

    private static int[] getPrices() {
        int[] prices = new int[10000];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = i + 1;
        }
        return prices;
    }
}
