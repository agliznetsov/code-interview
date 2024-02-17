package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

class FurthestBuilding {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heightDifferences = new PriorityQueue<>();

        // Iterate through the array of building heights.
        for (int i = 0; i < heights.length - 1; i++) {
            // Current building height and the next building height.
            int currentHeight = heights[i];
            int nextHeight = heights[i + 1];

            // Calculate the height difference between the current and next building.
            int diff = nextHeight - currentHeight;

            // If the next building is taller, a climb is needed.
            if (diff > 0) {
                // Add the height difference to the priority queue.
                heightDifferences.offer(diff);

                // If we have used more ladders than available, we use bricks.
                if (heightDifferences.size() > ladders) {
                    // Remove the smallest height difference and use bricks to climb up.
                    bricks -= heightDifferences.poll();

                    // If we do not have enough bricks to climb, return the current index.
                    if (bricks < 0) {
                        return i;
                    }
                }
            }
        }

        // If we can climb all buildings, return the last building index.
        return heights.length - 1;
    }

    @Test
    void test() {
        assertEquals(4, furthestBuilding(new int[] {4, 2, 7, 6, 9, 14, 12}, 5, 1));
    }

    @Test
    void test2() {
        assertEquals(7, furthestBuilding(new int[] {4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
    }
}
