package code.leetcode.hard;

import static code.utils.ArrayUtils.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.Test;

class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int numsLength = nums.length;
        int[] res = new int[numsLength - k + 1]; // Array to store the max values for each window
        Deque<Integer> deque = new ArrayDeque<>(); // Double-ended queue to maintain the max element indices

        for (int i = 0, j = 0; i < numsLength; ++i) {
            // Remove the indices of elements from the deque that are out of the current window
            if (!deque.isEmpty() && i - k + 1 > deque.peekFirst()) {
                deque.pollFirst();
            }

            // Remove indices of elements from the deque that are less than
            // the current element nums[i] since they are not useful
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add current element's index to the deque
            deque.offer(i);
            System.out.println(deque);

            // When we've hit size k, add the current max to the result
            // This corresponds to the index at the front of the deque
            if (i >= k - 1) {
                res[j++] = nums[deque.peekFirst()];
            }
        }

        // Return the populated result array containing max of each sliding window
        return res;
    }

    @Test
    void test() {
        int[] res = maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3);
        assertEquals(List.of(3, 3, 5, 5, 6, 7), toList(res));
    }

    @Test
    void test2() {
        int[] res = maxSlidingWindow(new int[] {-7, -8, 7, 5, 7, 1, 6, 0}, 4);
        assertEquals(List.of(7,7,7,7,7), toList(res));
    }

    @Test
    void test3() {
        int[] res = maxSlidingWindow(new int[] {11,12,13,14,15,16,17,18,19}, 4);
        assertEquals(List.of(4,5,6,7,8,9), toList(res));
    }

    @Test
    void test4() {
        int[] res = maxSlidingWindow(new int[] {19,18,17,16,15,14,13,12,11}, 4);
        assertEquals(List.of(9,8,7,6,5,4), toList(res));
    }
}
