package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.jupiter.api.Test;

class JumpGame6 {
    public int maxResultDP(int[] nums, int k) {
        int[] cells = new int[nums.length];
        Arrays.fill(cells, Integer.MIN_VALUE);
        cells[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int n = 1; n <= k && i + n < cells.length; n++) {
                int nextValue = cells[i] + nums[i + n];
                cells[i + n] = Math.max(cells[i + n], nextValue);
            }
        }
        return cells[cells.length - 1];
    }

    public int maxResult(int[] nums, int k) {
        // The length of the given array.
        int n = nums.length;
        // An array to store the maximum score that can be achieved upto that index.
        int[] dp = new int[n];
        // A deque to keep track of indexes whose scores are relevant for the current position.
        Deque<Integer> indexDeque = new ArrayDeque<>();
        indexDeque.offer(0);

        for (int i = 0; i < n; ++i) {
            // If the current index is out of the range of k from the start of the deque,
            // remove that index as it is no longer relevant.
            if (i - indexDeque.peekFirst() > k) {
                indexDeque.pollFirst();
            }

            // The maximum score at the current index is the sum of the value at this index
            // and the maximum score within the last k indexes.
            dp[i] = nums[i] + dp[indexDeque.peekFirst()];

            // If the score at the current index is greater than or equal to the score at
            // the indexes stored at the end of the deque, remove those indexes.
            while (!indexDeque.isEmpty() && dp[indexDeque.peekLast()] <= dp[i]) {
                indexDeque.pollLast();
            }

            // Add the current index to the end of the deque as it might be useful
            // for the upcoming indexes.
            indexDeque.offerLast(i);
        }

        // Return the maximum score that can be achieved at the last index.
        return dp[n - 1];
    }

    @Test
    void test() {
        assertEquals(7, maxResult(new int[] {1, -1, -2, 4, -7, 3}, 2));
    }

    @Test
    void test2() {
        assertEquals(17, maxResult(new int[] {10, -5, -2, 4, 0, 3}, 3));
    }

    @Test
    void test3() {
        assertEquals(0, maxResult(new int[] {1, -5, -20, 4, -1, 3, -6, -3}, 2));
    }
}
