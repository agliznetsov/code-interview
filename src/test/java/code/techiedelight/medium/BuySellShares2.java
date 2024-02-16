package code.techiedelight.medium;
/*

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.


*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class BuySellShares2 {
    public static int findMaxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //        BruteForce solver = new BruteForce(prices);
        //        int profit = solver.find(0, 0, 0);
        //        System.out.println("hit " + solver.hit + " miss " + solver.miss);
        //        return profit;
        return find(prices);
    }

    public static int find(int[] prices) {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;

        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }

        return sell2;
    }

    private static class BruteForce {
        final int[] prices;
        final Map<String,Integer> cache = new HashMap<>();
        public long hit = 0;
        public int miss = 0;

        private BruteForce(int[] prices) {
            this.prices = prices;
        }

        public int find(int day, int shares, int transactions) {
            if (day == prices.length - 1) {
                return prices[day] * shares;
            }

            String key = day + "." + shares + "." + transactions;
            Integer profit = cache.get(key);
            if (profit == null) {
                miss++;

                int a = -1;
                int b = -1;

                //do nothing
                int c = find(day + 1, shares, transactions);

                if (shares == 0 && transactions < 2) {
                    // buy
                    a = find(day + 1, 1, transactions + 1) - prices[day];
                } else if (shares == 1) {
                    // sell
                    b = find(day + 1, 0, transactions) + prices[day];
                }

                profit = Math.max(c, Math.max(a, b));
                cache.put(key, profit);
            } else {
                hit++;
            }

            return profit;
        }
    }

    @Test
    void test() {
        assertEquals(6, findMaxProfit(new int[] {3, 3, 5, 0, 0, 3, 1, 4}));
        assertEquals(13, findMaxProfit(new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
    }
}
