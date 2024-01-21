package techiedelight.medium;
/*

Given a positive integer `n`, find all combinations of numbers between 1 and `n` having sum `n`.

Input : n = 4
Output: {[4], [1, 3], [2, 2], [1, 1, 2], [1, 1, 1, 1]}

Input : n = 5
Output: {[5], [1, 4], [2, 3], [1, 1, 3], [1, 2, 2], [1, 1, 1, 2], [1, 1, 1, 1, 1]}

The solution should return a set containing all the distinct combinations in increasing order.

*/


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class Combinations5 {
    public static Set<List<Integer>> findCombinations(int n) {
        if (n == 0) {
            return Set.of();
        }
        Set<List<Integer>> set = new HashSet<>();
        addDigit(set, List.of(), n);
        return set;
    }

    private static void addDigit(Set<List<Integer>> set, List<Integer> seq, int sum) {
        if (sum == 0) {
            seq.sort(Integer::compareTo);
            set.add(seq);
            return;
        }

        for (int i = 1; i <= sum; i++) {
            List<Integer> newSeq = new ArrayList<>(seq);
            newSeq.add(i);
            addDigit(set, newSeq, sum - i);
        }
    }

    @Test
    void test() {
        var nums = findCombinations(4);
        System.out.println(nums);
        assertEquals(5, nums.size());
    }
}
