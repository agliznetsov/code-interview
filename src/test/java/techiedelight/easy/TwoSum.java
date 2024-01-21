package techiedelight.easy;

/*

Given an unsorted integer array, find a pair with the given sum in it.

• Each input can have multiple solutions. The output should match with either one of them.

Input : nums[] = [8, 7, 2, 5, 3, 1], target = 10
Output: (8, 2) or (7, 3)

• The solution can return pair in any order. If no pair with the given sum exists, the solution should return null.

Input : nums[] = [5, 2, 6, 8, 1, 9], target = 12
Output: null

*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import techiedelight.Pair;

class TwoSum {
	/* The Pair<U, V> class have
		1. Two member variables, first and second.
		2. Factory method `Pair.of(U, V)` for creating its immutable instance.
		3. equals() and hashCode() methods overridden.
	*/

    public static Pair<Integer,Integer> findPair(int[] nums, int target) {
        //        return searchArray(nums, target);
        //        return findComplement(nums, target);
        return findTwoPointers(nums, target);
    }

    private static Pair<Integer,Integer> findTwoPointers(int[] nums, int target) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return Pair.of(nums[left], nums[right]);
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return null;
    }

    private static Pair<Integer,Integer> findComplement(int[] nums, int target) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;

            if (numSet.contains(complement)) {
                return Pair.of(num, complement);
            }

            numSet.add(num);
        }

        return null;
    }

    private static Pair<Integer,Integer> searchArray(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    if (nums[i] + nums[j] == target) {
                        return Pair.of(i, j);
                    }
                }
            }
        }
        return null;
    }


    public static Set<Pair<Integer,Integer>> findPairs(List<Integer> nums, int k) {
        Set<Pair<Integer,Integer>> pairs = new HashSet<>();
        Set<Integer> complements = new HashSet<>();

        k = Math.abs(k);

        for (Integer num : nums) {
            Integer complement = num + k;
            if (complements.contains(complement)) {
                pairs.add(Pair.of(num, complement));
            }
            complement = num - k;
            if (complements.contains(complement)) {
                pairs.add(Pair.of(complement, num));
            }
            complements.add(num);
        }

        return pairs;
    }

}
