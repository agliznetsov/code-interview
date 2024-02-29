package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class StockPriceTest {
    class StockPrice {

        // HashMap for storing timestamp and respective price.
        private HashMap<Integer, Integer> timestampToPriceMap = new HashMap<>();

        // TreeMap for keeping track of the number of instances of a price level.
        private TreeMap<Integer, Integer> priceFrequencyMap = new TreeMap<>();

        // Variable to store the last timestamp processed.
        private int latestTimestamp;

        // Constructor
        public StockPrice() {}

        // Updates the price of the stock at the given timestamp
        public void update(int timestamp, int price) {
            // If the timestamp already exists, update the frequency of the old price
            if (timestampToPriceMap.containsKey(timestamp)) {
                int oldPrice = timestampToPriceMap.get(timestamp);
                // Decrease the frequency of the old price; if it reaches 0, remove it from the TreeMap
                if (priceFrequencyMap.merge(oldPrice, -1, Integer::sum) == 0) {
                    priceFrequencyMap.remove(oldPrice);
                }
            }

            // Update the timestamp to map to the new price
            timestampToPriceMap.put(timestamp, price);
            // Increase the frequency of the new price, adding it if it's not already present
            priceFrequencyMap.merge(price, 1, Integer::sum);
            // Update the latest timestamp to the current one if it's the latest
            latestTimestamp = Math.max(latestTimestamp, timestamp);
        }

        // Gets the latest price of the stock
        public int current() {
            return timestampToPriceMap.get(latestTimestamp);
        }

        // Returns the maximum stock price recorded so far
        public int maximum() {
            return priceFrequencyMap.lastKey();
        }

        // Returns the minimum stock price recorded so far
        public int minimum() {
            return priceFrequencyMap.firstKey();
        }

    }

    @Test
    void test() {
        StockPrice sp = new StockPrice();
        sp.update(1, 10);
        sp.update(2, 5);
        assertEquals(10, sp.maximum());
        assertEquals(5, sp.minimum());
        sp.update(1, 3);
        assertEquals(5, sp.maximum());
        assertEquals(3, sp.minimum());
    }
}
