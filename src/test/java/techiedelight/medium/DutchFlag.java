package techiedelight.medium;
/*

Given an array containing only 0’s, 1’s, and 2’s, in-place sort it in linear time and using constant space.

Input : [0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0]
Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2]

*/


import static techiedelight.ArrayUtils.swap;
import static techiedelight.ArrayUtils.toList;

import org.junit.jupiter.api.Test;

class DutchFlag {
    public static void countingSort(int[] nums) {
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (nums[i] == 1) {
                oneCount++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < zeroCount) {
                nums[i] = 0;
            } else if (i < zeroCount + oneCount) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    public static void sortArray(int[] nums) {
        int start = partition(nums, 0, 1);
        partition(nums, start, 2);
    }

    // Linear time partition routine to sort an array containing 0, 1, and 2.
    // It is similar to 3–way partitioning for the Dutch national flag problem.
    public static void threeWayPartition(int[] nums) {
        int start = 0, mid = 0;
        int pivot = 1;
        int end = nums.length - 1;

        while (mid <= end) {
            if (nums[mid] < pivot)         // current element is 0
            {
                swap(nums, start, mid);
                start++;
                mid++;
            } else if (nums[mid] > pivot)    // current element is 2
            {
                swap(nums, mid, end);
                end--;
            } else {                      // current element is 1
                mid++;
            }
        }
    }

    public static int partition(int nums[], int start, int pivot) {
        int j = start;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, j);
                j++;
            }
        }
        return j;
    }

    @Test
    void test() {
        int[] nums = new int[] {0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0};
        System.out.println(toList(nums));
        threeWayPartition(nums);
        System.out.println(toList(nums));
    }
}
