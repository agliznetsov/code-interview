package techiedelight.medium;
/*

Given a list containing future prediction of share prices, find the maximum profit earned by buying and selling shares any number of times with the constraint, a new transaction can only start after the previous transaction is complete, i.e., you can only hold at most one share at a time.

Input : [1, 5, 2, 3, 7, 6, 4, 5]
Output: 10
Explanation: Total profit earned is 10

Buy on day 1 and sell on day 2
Buy on day 3 and sell on day 5
Buy on day 7 and sell on day 8


Input : [10, 8, 6, 5, 4, 2]
Output: 0

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BuySellShares {
    public static int findMaxProfit(int[] price) {
        if (price == null || price.length < 2) {
            return 0;
        }
        //		return new BruteForce(price).find(0, 0, 0);
        return find(price);
    }

	public static int find(int[] price) {
		int profit = 0;
		int min = price[0];

		for (int i = 1; i < price.length - 1; i++) {
			min = Math.min(min, price[i]);
			if (price[i] > price[i + 1] && price[i] > min) {
				profit += price[i] - min;
				min = Integer.MAX_VALUE;
			}
		}

		if (price[price.length - 1] > min) {
			profit += price[price.length - 1] - min;
		}

		return profit;
	}

    private static class BruteForce {
        final int[] price;

        private BruteForce(int[] price) {
            this.price = price;
        }

        public int find(int day, int money, int shares) {
            if (day == price.length - 1) {
                if (shares == 1) {
                    money += price[day];
                }
                return money;
            }

            int a = Integer.MIN_VALUE;
            int b = Integer.MIN_VALUE;

            //do nothing
            int c = find(day + 1, money, shares);

            if (shares == 0) {
                // buy
                a = find(day + 1, money - price[day], 1);
            } else {
                // sell
                b = find(day + 1, money + price[day], 0);
            }

            return Math.max(c, Math.max(a, b));
        }
    }


    @Test
    void test() {
        assertEquals(3, findMaxProfit(new int[] {1, 4}));
        assertEquals(0, findMaxProfit(new int[] {4, 1}));
        assertEquals(20, findMaxProfit(new int[] {10, 20, 30}));
        assertEquals(10, findMaxProfit(new int[] {1, 5, 2, 3, 7, 6, 4, 5}));
    }
}
