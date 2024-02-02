package techiedelight.medium;
/*

Given a set of positive integers, check if it can be divided into two subsets with equal sum.

Input: S = [3, 1, 1, 2, 2, 1]
Output: true
Explanation: S can be partitioned into two partitions, each having a sum of 5.

S1 = [1, 1, 1, 2]
S2 = [2, 3]

Note that this solution is not unique. Here’s another solution.

S1 = [3, 1, 1]
S2 = [2, 2, 1]

*/

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PartitionProblem {
    public static boolean partition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        } else {
            return new Solver(nums).partition(0, 0, 0);
        }
    }

    private static class Solver {
        private final int[] nums;

        private Solver(int[] nums) {
            this.nums = nums;
        }

        public boolean partition(int sumLeft, int sumRight, int index) {
            //TODO: add memorization
            if (index == nums.length) {
                return sumLeft == sumRight;
            }
            return partition(sumLeft + nums[index], sumRight, index + 1)
                    || partition(sumLeft, sumRight + nums[index], index + 1);
        }
    }

    @Test
    void test() {
        assertTrue(partition(new int[] {3, 1, 1, 2, 2, 1}));
    }
}
