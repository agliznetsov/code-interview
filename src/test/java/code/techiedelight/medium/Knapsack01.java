package code.techiedelight.medium;
/*

Given a set of items, each with a weight and a value, determine the number of each item to include in a collection
so that the total weight is less than or equal to a given limit and the total value is as large as possible.
Note that the items are indivisible; we can either take an item or not (0-1 property).

Input:

value = [20, 5, 10, 40, 15, 25]
weight = [1, 2, 3, 8, 7, 4]
int W = 10

Output: 60

Explanation: Knapsack value is 60

value = 20 + 40 = 60
weight = 1 + 8 = 9 <= W

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import code.utils.ArrayUtils;

class Knapsack01 {

    public static int findTabular(int[] values, int[] weights, int maxWeight) {
        // `T[i][j]` stores the maximum value of knapsack having weight
        // less than equal to `j` with only first `i` items considered.
        int[][] mat = new int[values.length + 1][maxWeight + 1];

        // do for i'th item
        for (int i = 1; i <= values.length; i++)
        {
            // consider all weights from 0 to maximum capacity `W`
            for (int j = 0; j <= maxWeight; j++)
            {
                // don't include the i'th element if `j-w[i-1]` is negative
                if (weights[i-1] > j) {
                    mat[i][j] = mat[i-1][j];
                }
                else {
                    // find the maximum value we get by excluding or including
                    // the i'th item
                    mat[i][j] = Integer.max(mat[i-1][j], mat[i-1][j-weights[i-1]] + values[i-1]);
                }
            }
        }

        ArrayUtils.print(mat);

        // return maximum value
        return mat[values.length][maxWeight];
    }


    private static class RecursiveSolution {
        final int[] values;
        final int[] weights;
        final HashMap<String , MaxValue> cache = new HashMap<>();

        private RecursiveSolution(int[] values, int[] weights) {
            this.values = values;
            this.weights = weights;
        }

        public MaxValue findMaxValue(int n, int weight) {
            var key = n + "." + "." + weight;
            var res = cache.get(key);
            if (res == null) {
                if (n >= values.length) {
                    return new MaxValue(0, Collections.emptyList());
                } else {
                    if (weights[n] > weight) {
                        res = findMaxValue(n + 1, weight);
                    } else {
                        var skip = findMaxValue(n + 1, weight);
                        var take = findMaxValue(n + 1, weight - weights[n]);
                        if (skip.value >= take.value + values[n]) {
                            res = skip;
                        } else {
                            res = new MaxValue(take.value + values[n], combine(take.items, n));
                        }
                    }
                }
                cache.put(key, res);
            }
            return res;
        }

        private List<Integer> combine(List<Integer> items, int n) {
            var res = new ArrayList<>(items);
            res.add(n);
            return res;
        }
    }

    public static class MaxValue {
        public final int value;
        public final List<Integer> items;

        public MaxValue(int value, List<Integer> items) {
            this.value = value;
            this.items = items;
        }
    }

    public static int findKnapsackValue(int[] values, int[] weights, int maxWeight) {
        return new RecursiveSolution(values, weights).findMaxValue(0, maxWeight).value;
    }

    @Test
    void tabularTest() {
        assertEquals(60, findTabular(new int[] {20, 5, 10, 40, 15, 25}, new int[] {1, 2, 3, 8, 7, 4}, 10));
    }

    @Test
    void tabularTest2() {
        assertEquals(115, findTabular(new int[] {20, 5, 10, 40, 15, 25}, new int[] {1, 2, 3, 8, 7, 4}, 25));
    }

    @Test
    void recursiveTest() {
        var res = new RecursiveSolution(new int[] {20, 5, 10, 40, 15, 25}, new int[] {1, 2, 3, 8, 7, 4}).findMaxValue(0, 10);
        System.out.println(res.items);
        assertEquals(60, res.value);
    }

    @Test
    void recursiveTest2() {
        var res = new RecursiveSolution(new int[] {20, 5, 10, 40, 15, 25}, new int[] {1, 2, 3, 8, 7, 4}).findMaxValue(0, 25);
        System.out.println(res.items);
        assertEquals(115, res.value);
    }

    @Test
    @Disabled
    void benchmarkRecursive() {
        int[] values = new int[1000];
        int[] weights = new int[1000];
        Arrays.fill(values, 10);
        Arrays.fill(weights, 100);
        new RecursiveSolution(values, weights).findMaxValue(0, 1_000_000);
    }

    @Test
    @Disabled
    void benchmarkTabular() {
        int[] values = new int[1000];
        int[] weights = new int[1000];
        Arrays.fill(values, 10);
        Arrays.fill(weights, 100);
        findTabular(values, weights, 1_000_000);
    }
}
