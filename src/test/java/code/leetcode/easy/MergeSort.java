package code.leetcode.easy;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MergeSort {

    int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int ia = 0, ib = 0;
        for (int i = 0; i < c.length; i++) {
            if (ia < a.length && ib < b.length) {
                if (a[ia] < b[ib]) {
                    c[i] = a[ia];
                    ia++;
                } else {
                    c[i] = b[ib];
                    ib++;
                }
            } else {
                if (ia < a.length) {
                    c[i] = a[ia];
                    ia++;
                } else {
                    c[i] = b[ib];
                    ib++;
                }
            }
        }
        return c;
    }

    int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        int middle = nums.length / 2;
        var left = Arrays.copyOfRange(nums, 0, middle);
        var right = Arrays.copyOfRange(nums, middle, nums.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    int[] selectionSort(int[] nums) {
        for (int start = 0; start < nums.length; start++) {
            int index = -1;
            int minValue = Integer.MAX_VALUE;
            for (int i = start; i < nums.length; i++) {
                if (nums[i] < minValue) {
                    index = i;
                    minValue = nums[i];
                }
            }
            nums[index] = nums[start];
            nums[start] = minValue;
        }
        return nums;
    }

    @Test
    void test() {
        var random = new Random();
        int[] nums = new int[200_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt();
        }

        // selection = 9s, merge = 0.026
        var start = System.currentTimeMillis();
        mergeSort(nums);
        var end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);
    }
}
