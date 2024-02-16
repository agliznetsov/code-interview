package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JumpGame {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxReach = 0; // Farthest index you can reach
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false; // Cannot reach the current index
            }
            maxReach = Math.max(maxReach, i + nums[i]); // Update the farthest index you can reach
            if (maxReach >= n - 1) {
                return true; // Can reach the last index
            }
        }
        return false; // Cannot reach the last index
    }

    @Test
    void test() {
        assertTrue(canJump(new int[] {2, 3, 1, 1, 4}));
    }

    @Test
    void test2() {
        assertFalse(canJump(new int[] {3, 2, 1, 0, 4}));
    }
}
