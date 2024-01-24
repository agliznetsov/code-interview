package techiedelight.medium;
/*

Given a set S of positive integers, determine if it can be partitioned into three disjoint subsets that all have the same sum, and they cover S.

Input: S = [7, 3, 2, 1, 5, 4, 8]
Output: true
Explanation: S can be partitioned into three partitions [[7, 3], [5, 4, 1], [8, 2]], each having a sum of 10.

Note that there can be multiple solutions to a single set.

*/

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class Partition3 {
    public static boolean partition(int[] nums) {
	    if (nums.length < 3) {
		    return false;
	    }
        var solver = new Solver(nums);
        var res = solver.partition(0, 0, 0, 0);
        System.out.println(solver.counter);
        return res;
    }

    public static class Solver {
        private final int[] nums;
        public long counter = 0;
        private Map<String, Boolean> states = new HashMap<>();

        public Solver(int[] nums) {
            this.nums = nums;
        }

        public boolean partition(int n, int sum1, int sum2, int sum3) {
            counter++;
            var state = n + "." + sum1 + "." + sum2 + "." + sum3;
            var res = states.get(state);
            if (res == null) {
                if (n == nums.length) {
                    res = sum1 == sum2 && sum2 == sum3;
                } else {
                    int num = nums[n];
                    res = partition(n + 1, sum1 + num, sum2, sum3)
                            || partition(n + 1, sum1, sum2 + num, sum3)
                            || partition(n + 1, sum1, sum2, sum3 + num);
                }
                states.put(state, res);
            }
            return res;
        }
    }

    @Test
    void test() {
		assertTrue(partition(new int[]{7, 3, 2, 1, 5, 4, 8}));
    }

    @Test
    void test10() {
        // 8ms, 88573 vs 661
        benchmark(10);
    }

    @Test
    void test20() {
        // 11s, 5230176601 vs 3ms 4621
        benchmark(20);
    }

    @Test
    void test30() {
        // vs 2ms 16369
        benchmark(31);
    }

    private static void benchmark(int count) {
        int[] nums = new int[count];
        Arrays.fill(nums, 1);
        partition(nums);
    }
}
