package code.techiedelight.medium;/*

Given an integer array, find all distinct combinations of a given length `k`. The solution should return a set containing all the distinct combinations, while preserving the relative order of elements as they appear in the array.

Input : [2, 3, 4], k = 2
Output: {[2, 3], [2, 4], [3, 4]}

Input : [1, 2, 1], k = 2
Output: {[1, 2], [1, 1], [2, 1]}

Input : [1, 1, 1], k = 2
Output: {[1, 1]}

Input : [1, 2, 3], k = 4
Output: {}

Input : [1, 2], k = 0
Output: {[]}

*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class Combinations {
    public static Set<List<Integer>> findCombinations(List<Integer> nums, int k) {
        if (k == 0) {
            if (nums.isEmpty()) {
                return Set.of();
            } else {
                return Set.of(List.of());
            }
        }
        Set<List<Integer>> set = new HashSet<>();
        addDigit(nums, set, List.of(), 0, k);
        return set;
    }

    private static void addDigit(List<Integer> nums, Set<List<Integer>> set, List<Integer> seq, int pos, int length) {
        if (seq.size() == length) {
            set.add(seq);
            return;
        }

        for (int i = pos; i < nums.size(); i++) {
            List<Integer> newSeq = new ArrayList<>(seq);
            newSeq.add(nums.get(i));
            addDigit(nums, set, newSeq, i + 1, length);
        }
    }

    @Test
    void test() {
        var nums = findCombinations(List.of(2, 3, 4), 2);
        assertEquals(3, nums.size());
        assertTrue(nums.contains(List.of(2, 3)));
        assertTrue(nums.contains(List.of(3, 4)));
        assertTrue(nums.contains(List.of(2, 4)));

    }
}
