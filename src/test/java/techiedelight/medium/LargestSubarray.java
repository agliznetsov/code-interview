package techiedelight.medium;
/*

Given a binary array containing 0’s and 1’s, find the largest contiguous subarray with equal numbers of 0’s and 1’s.

Input : [0, 0, 1, 0, 1, 0, 0]
Output: [0, 1, 0, 1] or [1, 0, 1, 0]

Input : [0, 0, 0, 0]
Output: []

Note: Since an input can contain several largest subarrays with equal numbers of 0’s and 1’s, the code should return any one of them.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LargestSubarray {

    public static List<Integer> findLargestSubarray(List<Integer> nums) {
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
            int num = nums.get(i);
            target += num == 0 ? -1 : 1;

            // if the sum is seen for the first time, insert the sum with its
            // into the map
            map.putIfAbsent(target, i);

            // update length and ending index of the maximum length subarray
            // having sum `S`
            Integer start = map.get(target);
            if (start != null && len < i - start) {
                len = i - start;
                ending_index = i;
            }
        }

        return nums.subList(ending_index - len + 1, ending_index + 1);
    }

    @Test
    void test() {
        assertEquals(List.of(0, 1, 0, 1),
                findLargestSubarray(List.of(0, 0, 1, 0, 1, 0, 0)));
    }


}
