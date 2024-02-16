package code.techiedelight.medium;/*

Given an integer array, find the maximum length contiguous subarray having a given sum.

Input : nums[] = [5, 6, -5, 5, 3, 5, 3, -2, 0], target = 8
Output: [-5, 5, 3, 5]
Explanation: The subarrays with sum 8 are [[-5, 5, 3, 5], [3, 5], [5, 3]]. The longest subarray is [-5, 5, 3, 5] having length 4.

Note: Since an input can contain several maximum length subarrays with given sum, the solution should return any one of the maximum length subarray.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MaximumLengthSubarray {

    public static List<Integer> bruteForce(List<Integer> nums, int target) {
        List<Integer> res = List.of();

        for (int i = 0; i < nums.size(); i++) {
            int sum = 0;
            for (int j = i; j < nums.size(); j++) {
                sum += nums.get(j);
                if (sum == target && j - i >= res.size()) {
                    res = nums.subList(i, j + 1);
                }
            }
        }

        return res;
    }

    public static List<Integer> findMaxLenSubarray(List<Integer> nums, int S) {
        // create an empty HashMap to store the ending index of the first
        // subarray having some sum
        Map<Integer,Integer> map = new HashMap<>();

        // insert (0, -1) pair into the set to handle the case when a
        // subarray with sum `S` starts from index 0
        map.put(0, -1);

        int target = 0;

        // `len` stores the maximum length of subarray with sum `S`
        int len = 0;

        // stores ending index of the maximum length subarray having sum `S`
        int ending_index = -1;

        // traverse the given array
        for (int i = 0; i < nums.size(); i++) {
            // sum of elements so far
            target += nums.get(i);

            // if the sum is seen for the first time, insert the sum with its
            // into the map
            map.putIfAbsent(target, i);

            // update length and ending index of the maximum length subarray
            // having sum `S`
            if (map.containsKey(target - S) && len < i - map.get(target - S)) {
                len = i - map.get(target - S);
                ending_index = i;
            }
        }

        return nums.subList(ending_index - len + 1, ending_index + 1);
    }

    @Test
    void test() {
        assertEquals(List.of(-5, 5, 3, 5),
                findMaxLenSubarray(List.of(5, 6, -5, 5, 3, 5, 3, -2, 0), 8));
    }

    @Test
    void test2() {
        assertEquals(List.of(1),
                findMaxLenSubarray(List.of(1), 1));
    }

    @Test
    void test3() {
        // [], Target = -4
        assertEquals(List.of(1, -5),
                findMaxLenSubarray(List.of(5, 1, -5, 1, 2), -4));
    }
}
