package techiedelight.medium;
/*

Given an integer array, find all contiguous subarrays with zero-sum.

Input : [4, 2, -3, -1, 0, 4]
Output: {[-3, -1, 0, 4], [0]}

Input : [3, 4, -7, 3, 1, 3, 1, -4, -2, -2]
Output: {[3, 4, -7], [4, -7, 3], [-7, 3, 1, 3], [3, 1, -4], [3, 1, 3, 1, -4, -2, -2], [3, 4, -7, 3, 1, 3, 1, -4, -2, -2]}

Input : [0, 0]
Output: {[0], [0, 0]}

Input : [1, 2, 3]
Output: {}

Note: Since an input can have multiple subarrays with zero-sum, the solution should return a set containing all the distinct subarrays.


*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ZeroSum2 {

    public static Set<List<Integer>> bruteForce(List<Integer> nums) {
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.size(); i++) {
            int sum = 0;
            for (int j = i; j < nums.size(); j++) {
                sum += nums.get(j);
                if (sum == 0) {
                    res.add(nums.subList(i, j + 1));
                }
            }
        }

        return res;
    }

    private static<K, V> void insert(Map<K, List<V>> hashMap, K key, V value)
    {
        // if the key is seen for the first time, initialize the list
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }

    // Function to print all subarrays with a zero-sum in a given array
    public static Set<List<Integer>> getAllZeroSumSubarrays(List<Integer> nums)
    {
        Set<List<Integer>> set = new HashSet<>();

        // create an empty multimap to store the ending index of all
        // subarrays having the same sum
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        // insert (0, -1) pair into the map to handle the case when
        // subarray with zero-sum starts from index 0
        insert(hashMap, 0, -1);

        int sum = 0;

        // traverse the given array
        for (int i = 0; i < nums.size(); i++)
        {
            // sum of elements so far
            sum += nums.get(i);

            // if the sum is seen before, there exists at least one
            // subarray with zero-sum
            if (hashMap.containsKey(sum))
            {
                List<Integer> list = hashMap.get(sum);

                // find all subarrays with the same sum
                for (Integer value: list)
                {
                    System.out.println("Subarray [" + (value + 1) + "…" + i + "]");
                    set.add(nums.subList(value + 1, i + 1));
                }
            }

            // insert (sum so far, current index) pair into the multimap
            insert(hashMap, sum, i);
        }

        return set;
    }


    @Test
    void test1() {
        List<Integer> nums = List.of(3, 4, -7, 3, 1, 3, 1, -4, -2, -2);
        assertEquals(bruteForce(nums), getAllZeroSumSubarrays(nums));
    }

}
