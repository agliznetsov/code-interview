package code.leetcode.medium;

import static code.utils.ArrayUtils.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SortColors {
    public void sortColors(int[] nums) {
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int n : nums) {
            if (n == 0) {
                c0++;
            } else if (n == 1) {
                c1++;
            } else {
                c2++;
            }
        }
        int from = 0;
        if (c0 > 0) {
            Arrays.fill(nums, from, from + c0, 0);
            from += c0;
        }
        if (c1 > 0) {
            Arrays.fill(nums, from, from + c1, 1);
            from += c1;
        }
        if (c2 > 0) {
            Arrays.fill(nums, from, from + c2, 2);
        }
    }

    private static void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p2];
        nums[p2] = nums[p1];
        nums[p1] = tmp;
    }

    @Test
    void test() {
        int[] arr = new int[] {2, 0, 2, 1, 1, 0};
        sortColors(arr);
        assertEquals(List.of(0, 0, 1, 1, 2, 2), toList(arr));
    }

    @Test
    void test2() {
        int[] arr = new int[] {2, 0, 1};
        sortColors(arr);
        assertEquals(List.of(0, 1, 2), toList(arr));
    }


    @Test
    void test3() {
        int[] arr = new int[] {1, 0, 1};
        sortColors(arr);
        assertEquals(List.of(0, 1, 1), toList(arr));
    }
}
