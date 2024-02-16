package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TrappingRainWater {
    public int trapLineByLine(int[] height) {
        int res = 0;
        int h = 0;

        boolean cont = true;
        while (cont) {
            cont = false;
            int left = -1;
            int water = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > h) {
                    // wall
                    cont = true;
                    if (left == -1) {
                        left = i;
                    } else {
                        res += water;
                        water = 0;
                        left = i;
                    }
                } else {
                    // void
                    if (left > -1) {
                        water++;
                    }
                }
            }
            h++;
        }

        return res;
    }


    public int trap(int[] height) {
        int n = height.length;
        int water = 0;
        int maxLeft = height[0];

        int[] maxRight = new int[height.length];
        int mr = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; i--) {
            mr = Math.max(mr, height[i]);
            maxRight[i] = mr;
        }
        // For every element of the array
        // except first and last element
        for (int i = 1; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, height[i]);
            int w = Math.min(maxLeft, maxRight[i]) - height[i];
            water += w;

        }
        return water;
    }

    @Test
    void test1() {
        assertEquals(6, trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    void test2() {
        assertEquals(9, trap(new int[] {4, 2, 0, 3, 2, 5}));
    }
}
