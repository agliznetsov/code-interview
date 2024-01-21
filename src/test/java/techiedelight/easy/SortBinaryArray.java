package techiedelight.easy;
/*

Given a binary array, in-place sort it in linear time and constant space. The output should contain all zeroes, followed by all ones.

Input : [1, 0, 1, 0, 1, 0, 0, 1]
Output: [0, 0, 0, 0, 1, 1, 1, 1]

Input : [1, 1]
Output: [1, 1]

*/

import static techiedelight.ArrayUtils.swap;
import static techiedelight.ArrayUtils.toList;

import org.junit.jupiter.api.Test;

class SortBinaryArray {
    public static void countZeros(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i < zeroCount ? 0 : 1;
        }
    }

    // Function to sort a binary array in linear time using partition logic of QuickSort
    public static void partition(int nums[]) {
        int pivot = 1;
        int j = 0;

        // each time we encounter a 0, `j` is incremented, and
        // 0 is placed before the pivot
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, j);
                j++;
            }
        }
    }

    @Test
    void test() {
        int[] nums = new int[] {0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0};
        System.out.println(toList(nums));
        partition(nums);
        System.out.println(toList(nums));
    }
}
