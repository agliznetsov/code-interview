package code.techiedelight.easy;
/*

Given an integer array, find an index that divides it into two non-empty contiguous subarrays having an equal sum.

Input : [-1, 6, 3, 1, -2, 3, 3]
Output: 2
Explanation: The element 3 at index 2 divides the array into two non-empty subarrays `[-1, 6]` and `[1, -2, 3, 3]` having the same sum.

• The solution should return -1 if no partition is possible.

Input : [6, -5, 2, -4, 1]
Output: -1
Explanation: No equal sum partition possible.

• In case multiple partitions is possible, return the index of the first partition.

Input : [-1, -2, 2, -3]
Output: 1
Explanation: The index 1 and 2 divides the array into two equal sum subarrays. The solution should return index 1.

Input : [4, 2, -3, 4, -1, 0, 4]
Output: 1
Explanation: The index 1 and 3 divides the array into two equal sum subarrays. The solution should return index 1.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BreakPointIndex {
    public static int findBreakPoint(int[] nums) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
        }

        int mid = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            left += mid;
            mid = nums[i];
            right -= mid;
            if (left == right && i > 0) {
                return i;
            }
        }

        return -1;
    }

    @Test
    void test1() {
        assertEquals(2, findBreakPoint(new int[] {-1, 6, 3, 1, -2, 3, 3}));
    }
}
