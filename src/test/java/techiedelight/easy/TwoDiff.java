package techiedelight.easy;

/*

Given an unsorted integer array, find all pairs with a given difference `k` in it using constant space.

Input : nums = [1, 5, 2, 2, 2, 5, 5, 4], k = 3
Output: {(2, 5), (1, 4)}

Note:

• The solution should return the pair in sorted order. For instance, among pairs (5, 2) and (2, 5), only pair (2, 5) appeared in the output.

• Since the input can contain multiple pairs with a given difference, the solution should return a set containing all the distinct pairs. For instance, the pair (2, 5) appeared only once in the output.

*/

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import techiedelight.Pair;

class TwoDiff {
	/* The Pair<U, V> class have
		1. Two member variables, first and second.
		2. Factory method `Pair.of(U, V)` for creating its immutable instance.
		3. equals() and hashCode() methods overridden.
	*/

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
