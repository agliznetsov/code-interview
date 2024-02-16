package code.techiedelight.easy;

/*
#31

Given an array of positive and negative integers, in-place segregate them in linear time and constant space. The output should contain all negative numbers, followed by all positive numbers.

Input : [9, -3, 5, -2, -8, -6, 1, 3]
Output: [-3, -2, -8, -6, 9, 5, 1, 3] or [-3, -2, -8, -6, 9, 5, 1, 3] or any other valid combination.

Input : [-4, -2, -7, -9]
Output: [-4, -2, -7, -9] or any other valid combination.

Input : [2, 4, 3, 1, 5]
Output: [2, 4, 3, 1, 5] or any other valid combination.

*/

import static code.ArrayUtils.swap;
import static code.ArrayUtils.toList;

import org.junit.jupiter.api.Test;

class RearrangeArray4 {
    public static int[] rearrangeTwoPointers(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] >= 0 && nums[right] < 0) {
                swap(nums, left, right);
                left++;
                right--;
            } else if (nums[left] < 0 && nums[right] >= 0) {
                left++;
                right--;
            } else if (nums[left] < 0) {
                left++;
            } else if (nums[right] >= 0) {
                right--;
            }
        }

        return nums;
    }

    public static int[] partition(int[] nums) {
        int pIndex = 0;

        // each time we find a negative number, `pIndex` is incremented,
        // and that element would be placed before the pivot
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)    // pivot is 0
            {
                swap(nums, i, pIndex);
                pIndex++;
            }
        }

        return nums;
    }

    @Test
    void testTwoPointers() {
        System.out.println(toList(rearrangeTwoPointers(new int[] {9, -3, 5, -2, -8, -6, 1, 3})));
    }

    @Test
    void testPartition() {
        System.out.println(toList(partition(new int[] {9, -3, 5, -2, -8, -6, 1, 3})));
    }
}
