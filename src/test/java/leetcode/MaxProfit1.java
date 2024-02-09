package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MaxProfit1 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            profit = Math.max(profit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return profit;
    }

    @Test
    void test() {
        assertEquals(5, maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    }
}
