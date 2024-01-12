package techiedelight.easy;
/*

Given an unsorted integer array, print all greater elements than all elements present to their right.

Input : [10, 4, 6, 3, 5]
Output: [10, 6, 5]
Explanation: The elements that are greater than all elements to their right are 10, 6, and 5.

Note: The solution should return the elements in the same order as they appear in the input array.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GreaterElements
{
	public static List<Integer> findGreaterElements(List<Integer> nums)
	{
		List<Integer> res = new LinkedList<>();
		
		int max = Integer.MIN_VALUE;
		
		for(int i=nums.size() - 1; i >= 0; i--) {
			int num = nums.get(i);
			if (num > max) {
				res.add(0, num);
			}
			max = Math.max(num, max);
		}
		
		return res;
	}

	@Test
	void test1() {
		assertEquals(List.of(10, 6, 5), findGreaterElements(List.of(10, 4, 6, 3, 5)));
	}

	@Test
	void test2() {
		assertEquals(List.of(5, 4), findGreaterElements(List.of(1, 5, 2, 2, 2, 5, 5, 4)));
	}
}
