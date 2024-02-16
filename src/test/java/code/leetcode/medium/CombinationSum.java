package code.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CombinationSum {

    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        backtrack(new ArrayList<>(), target, 0);
        return result;
    }

    private void backtrack(List<Integer> tempList, int target, int start) {
//        System.out.println(tempList);
        if (target < 0) {
            return;
        } else if (target == 0) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                tempList.add(candidates[i]);
                // Use the same element multiple times, so start index doesn't change
                backtrack(tempList, target - candidates[i], i);
                tempList.remove(tempList.size() - 1); // Backtrack
            }
        }
    }

    @Test
    void test() {
        var res = combinationSum(new int[] {2, 3, 5, 8}, 8);
        System.out.println(res);
    }
}
