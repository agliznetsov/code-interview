package techiedelight.easy;
/*

Given an array containing positive and negative elements, find a subarray with alternating positive and negative elements, and in which the subarray is as long as possible.

Input : [1, -2, 6, 4, -3, 2, -4, -3]
Output: [4, -3, 2, -4]

Note that the longest alternating subarray might not be unique. In case the multiple alternating subarrays of maximum length exists, the solution can return any one of them.

Input : [1, -2, 6, 2, 4, -3, 2, 4, -3]
Output: [1, -2, 6] or [4, -3, 2]

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class LongestAlternatingSubarray {
    public static List<Integer> find(List<Integer> nums) {
        if (nums.size() < 2) {
            return nums;
        }

        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 0;

        int length = 0;
        int start = 0;

        for (int i = 1; i < nums.size(); i++) {
            if ((nums.get(i - 1) < 0 && nums.get(i) >= 0) || (nums.get(i - 1) >= 0 && nums.get(i) < 0)) {
                length++;
            } else {
                if (length > maxLength) {
                    maxLength = length;
                    maxStart = start;
                    maxEnd = i;
                }
                start = i;
                length = 0;
            }
        }

        if (length > maxLength) {
            maxLength = length;
            maxStart = start;
            maxEnd = nums.size();
        }

        if (maxLength == 0) {
            return List.of(nums.get(0));
        } else {
            return nums.subList(maxStart, maxEnd);
        }
    }

    @Test
    void test1() {
        assertEquals(List.of(4, -3, 2, -4), find(List.of(1, -2, 6, 4, -3, 2, -4, -3)));
    }

    @Test
    void test2() {
        assertEquals(List.of(-1, 2, -1), find(List.of(-1, 2, -1)));
    }
}
