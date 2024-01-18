package techiedelight.medium;
/*

Given an integer array, in-place rearrange it such that every second element becomes greater than its left and right elements.

• Assume that no duplicate elements are present in the input array.
• The solution should perform single traveral of the array.
• In case the multiple rearrangement exists, the solution can return any one of them.

Input : [1, 2, 3, 4, 5, 6, 7]
Output: [1, 3, 2, 5, 4, 7, 6] or [1, 5, 2, 6, 3, 7, 4], or any other valid combination..

Input : [6, 9, 2, 5, 1, 4]
Output: [6, 9, 2, 5, 1, 4] or [1, 5, 2, 6, 4, 9], or any other valid combination..

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RearrangeArray {
    public static void rearrange(List<Integer> nums) {
        for (int i = 1; i < nums.size(); i += 2) {
            if (nums.get(i - 1) > nums.get(i)) {
                swap(nums, i - 1, i);
            }
            if (i < nums.size() - 1) {
                if (nums.get(i + 1) > nums.get(i)) {
                    swap(nums, i, i + 1);
                }
            }
        }
    }

    public static void swap(List<Integer> nums, int left, int right) {
        int tmp = nums.get(left);
        nums.set(left, nums.get(right));
        nums.set(right, tmp);
    }

    @Test
    void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        rearrange(list);
        System.out.println(list);
        assertEquals(List.of(1, 3, 2, 5, 4, 7, 6), list);
    }
}
