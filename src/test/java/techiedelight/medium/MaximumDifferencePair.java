package techiedelight.medium;
/*

Given an integer array, find the maximum difference between two elements in it such that the smaller element appears before the larger element. If no such pair exists, return any negative number.

Input : [2, 7, 9, 5, 1, 3, 5]
Output: 7
Explanation: The pair with the maximum difference is (2, 9).

Input : [5, 4, 3, 2, 1]
Output: -1 (or any other negative number)
Explanation: No pair with the maximum difference exists.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MaximumDifferencePair {
    public static int findMaxDiff(int[] nums) {
        int min = nums[0];
        int diff = -1;
        for (int num : nums) {
            if (num > min) {
                diff = Math.max(diff, num - min);
            }
            min = Math.min(min, num);
        }
        return diff;
    }

    @Test
    void test() {
        assertEquals(7, findMaxDiff(new int[] {2, 7, 9, 5, 1, 3, 5}));
    }
}
