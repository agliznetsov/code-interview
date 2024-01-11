package techiedelight.easy;/*

Given a sorted integer array, find a pair in it having an absolute minimum sum.

Input : [-6, -5, -3, 0, 2, 4, 9]
Output: (-5, 4)
Explanation: (-5, 4) = abs(-5 + 4) = abs(-1) = 1, which is minimum among all pairs.

• Each input can have multiple solutions. The output should match with either one of them.

Input : [-6, -2, 0, 1, 5]
Output: (-6, 5) or (-2, 1) or (0, 1)

• The solution can return pair in any order. If no pair exists, the solution should return null.

Input : [1]
Output: null

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import techiedelight.Pair;

class MinSumPair {

    public static Pair<Integer,Integer> findPair(int[] nums) {
		if (nums.length < 2) {
			return null;
	    }

		int left = 0;
		int right = nums.length - 1;
	    int minSum = Math.abs(nums[left] + nums[right]);

		while (left < right) {
			if (right > left + 1 && Math.abs(nums[left] + nums[right - 1]) <= minSum) {
				right--;
			} else if (left < right -1 && Math.abs(nums[left + 1] + nums[right]) <= minSum) {
				left++;
			} else if (right - left > 2 && Math.abs(nums[left + 1] + nums[right - 1]) <= minSum) {
				right--;
				left++;
			} else {
				break;
			}
			minSum = Math.abs(nums[left] + nums[right]);
		}

        return Pair.of(nums[left], nums[right]);
    }

    @Test
    void test1() {
        assertEquals(Pair.of(-3, 2), findPair(new int[] {-6, -5, -3, 0, 2, 4, 9}));
    }

	@Test
	void test2() {
		assertEquals(Pair.of(1, 2), findPair(new int[] {1, 2, 3, 4, 5}));
	}

	@Test
	void test3() {
		assertEquals(Pair.of(-2, -1), findPair(new int[] {-5, -4, -3, -2, -1}));
	}

	@Test
	void test4() {
		assertEquals(Pair.of(0, 1), findPair(new int[] {-3, 0, 1, 5}));
	}

	@Test
	void test5() {
		assertEquals(Pair.of(1, 2), findPair(new int[] {1, 2, 2, 2, 4, 5, 5, 5}));
	}
}
