package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class JumpGame2 {
    public int jumpDP(int[] nums) {
        int[] cells = new int[nums.length];
        Arrays.fill(cells, Integer.MAX_VALUE);
        cells[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cells[i] < Integer.MAX_VALUE) {
                for (int n = 1; n <= nums[i] && i + n < cells.length; n++) {
                    cells[i + n] = Math.min(cells[i + n], cells[i] + 1);
                }
            }
        }
        return cells[cells.length - 1];
    }

    public int jumpGreedy(int[] nums) {
        int jumps = 0;
        int currentMaxReach = 0;
        int nextMaxReach = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            nextMaxReach = Math.max(nextMaxReach, i + nums[i]);

            if (i == currentMaxReach) {
                jumps++;
                currentMaxReach = nextMaxReach;

                if (currentMaxReach >= nums.length - 1) {
                    break; // If the last index is reachable, no need to iterate further
                }
            }
        }

        return jumps;
    }

    @Test
    void test() {
        assertEquals(2, jumpGreedy(new int[] {2, 3, 1, 1, 4}));
    }

}
