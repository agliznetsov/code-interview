package techiedelight.medium;
/*

Given an integer array, check if it contains a contiguous subarray having zero-sum.

Input : [3, 4, -7, 3, 1, 3, 1, -4, -2, -2]
Output: true
Explanation: The subarrays with zero-sum are

[3, 4, -7]
[4, -7, 3]
[-7, 3, 1, 3]
[3, 1, -4]
[3, 1, 3, 1, -4, -2, -2]
[3, 4, -7, 3, 1, 3, 1, -4, -2, -2]

Input : [4, -7, 1, -2, -1]
Output: false
Explanation: The subarray with zero-sum doesn't exist.

*/

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ZeroSum {

    public static boolean hasZeroSumSubarray_bruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean hasZeroSumSubarray(int[] nums) {
        Set<Integer> sums = new HashSet<>();
        int sum = 0;

        for (int num : nums) {
            sum += num;
            if (sum == 0) {
                return true;
            } else if (sums.contains(sum)) {
                return true;
            } else {
                sums.add(sum);
            }
        }

        return false;
    }

    public static void print(int[] nums) {
        int[] sums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "   ");
        }
        System.out.println();

        for (int i = 0; i < nums.length; i++) {
            System.out.print(sums[i] + "   ");
        }
        System.out.println();
    }

    @Test
    void test1() {
        int[] nums = new int[] {3, 4, -7, 3, 1, 3, 1, -4, -2, -2};
        print(nums);
        assertTrue(hasZeroSumSubarray(nums));
    }

    @Test
    void test2() {
        int[] nums = new int[] {4, -7, 1, -2, -1};
        print(nums);
        assertFalse(hasZeroSumSubarray(nums));
    }

    @Test
    void test3() {
        int[] nums = new int[] {0};
        assertTrue(hasZeroSumSubarray(nums));
    }
}
