package leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MedianOfThwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid = (nums1.length + nums2.length) / 2;
        boolean odd = (nums1.length + nums2.length) % 2 == 0;
        int a = 0;
        int b = 0;
        int i = 0;
        int p1 = 0;
        int p2 = 0;

        while (p1 < nums1.length && p2 < nums2.length && i <= mid) {
            b = a;
            if (nums1[p1] <= nums2[p2]) {
                a = nums1[p1];
                p1++;
            } else {
                a = nums2[p2];
                p2++;
            }
            i++;
        }

        while (p1 < nums1.length && i <= mid) {
            b = a;
            a = nums1[p1];
            p1++;
            i++;
        }

        while (p2 < nums2.length && i<= mid) {
            b = a;
            a = nums2[p2];
            p2++;
            i++;
        }


        if (odd) {
            return (a + b) / 2.0;
        } else {
            return a;
        }
    }

    @Test
    void test() {
        assertEquals(0.0, findMedianSortedArrays(new int[] {0, 0}, new int[] {0, 0}));
        assertEquals(2.0, findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        assertEquals(2.5, findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }
}
