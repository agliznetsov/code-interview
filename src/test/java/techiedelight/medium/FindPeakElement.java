package techiedelight.medium;
/*

Given an integer array `A`, find the peak element in it. An element `A[i]` is a peak element if it's greater than its neighbor(s). i.e.,

• A[i-1] <= A[i] >= A[i+1]	(for 0 < i < n-1)
• A[i-1] <= A[i]			(if i = n – 1)
• A[i] >= A[i+1]			(if i = 0)


Input: [8, 9, 10, 12, 15]
Output: 15

Input: [10, 8, 6, 5, 3, 2]
Output: 10

• There might be multiple peak elements in an array, the solution should report any peak element.

Input: [8, 9, 10, 2, 5, 6]
Output: 10 or 6

Input: [8, 9, 2, 5, 6, 3]
Output: 9 or 6

• If the peak element is not located, the procedure should return -1.

Input: []
Output: -1

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return findPeakElement(nums, 0, nums.length - 1);
    }

    public static int findPeakElement(int[] nums, int a, int b) {
        if (a == b) {
            return nums[a];
        }
        if (b - a == 1) {
            return Math.max(nums[a], nums[b]);
        } else {
            int mid = a + (b - a) / 2;
            if (nums[mid + 1] > nums[mid]) {
                return findPeakElement(nums, mid, b);
            } else {
                return findPeakElement(nums, a, mid);
            }
        }
    }

    @Test
    void test1() {
        assertEquals(6, findPeakElement(new int[] {1, 2, 3, 4, 5, 6}));
    }

    @Test
    void test2() {
        assertEquals(7, findPeakElement(new int[] {7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    void test3() {
        assertEquals(10, findPeakElement(new int[] {8, 9, 10, 2, 5, 6}));
    }
}
