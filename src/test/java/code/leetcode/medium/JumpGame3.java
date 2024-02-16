package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

class JumpGame3 {
    public boolean canReach(int[] nums, int start) {
        boolean[] visited = new boolean[nums.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        Integer pos;
        while ((pos = queue.poll()) != null) {
            int jump = nums[pos];
            if (jump == 0) {
                return true;
            } else {
                if (!visited[pos]) {
                    visited[pos] = true;
                    if (pos - jump >= 0) {
                        queue.add(pos - jump);
                    }
                    if (pos + jump < nums.length) {
                        queue.add(pos + jump);
                    }
                }
            }
        }
        return false;
    }

    @Test
    void test() {
        assertEquals(true, canReach(new int[] {4, 2, 3, 0, 3, 1, 2}, 5));
    }
}
