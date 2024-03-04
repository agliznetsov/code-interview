package code.leetcode.easy;

import static code.utils.ArrayUtils.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int begin = 0;
        int end = nums.length - 1;
        int pos = end;
        while (pos >= 0) {
            int a = nums[begin] * nums[begin];
            int b = nums[end] * nums[end];
            if (a > b) {
                res[pos] = a;
                begin++;
            } else {
                res[pos] = b;
                end--;
            }
            pos--;
        }
        return res;
    }

    @Test
    void test() {
        assertEquals(List.of(0, 1, 9, 16, 100), toList(sortedSquares(new int[] {-4, -1, 0, 3, 10})));
    }
}
