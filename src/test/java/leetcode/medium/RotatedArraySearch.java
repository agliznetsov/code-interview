package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RotatedArraySearch {
    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);
        if (pivot == -1) {
            return binarySearch(nums, 0, nums.length - 1, target);
        } else {
            int res = binarySearch(nums, 0, pivot, target);
            if (res >= 0) {
                return res;
            } else {
                return binarySearch(nums, pivot, nums.length - 1, target);
            }
        }
    }

    public int findPivot(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (left > 0 && arr[left] < arr[left - 1]) {
                return left;
            }
            int mid = left + (right - left) / 2;
            if (arr[left] > arr[mid]) {
                right = mid;
            } else if (arr[right] < arr[mid]) {
                left = mid + 1;
            } else {
                break;
            }
        }
        return -1;
    }

    public int binarySearch(int[] arr, int left, int right, int target) {
        // Perform binary search until the left pointer is less than or equal to the right pointer
        while (left <= right) {
            // Calculate the middle index of the array
            int mid = left + (right - left) / 2;

            // If the target is found at the middle index, return the index
            if (arr[mid] == target) {
                return mid;
            }
            // If the target is less than the element at the middle index, search in the left half
            else if (target < arr[mid]) {
                right = mid - 1;
            }
            // If the target is greater than the element at the middle index, search in the right half
            else {
                left = mid + 1;
            }
        }
        // If the target is not found in the array, return -1
        return -1;
    }

    @Test
    void testPivot() {
        assertEquals(-1, findPivot(new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
        assertEquals(7, findPivot(new int[] {1, 2, 3, 4, 5, 6, 7, 0}));
        assertEquals(6, findPivot(new int[] {2, 3, 4, 5, 6, 7, 0, 1}));
        assertEquals(1, findPivot(new int[] {7, 0, 1, 2, 3, 4, 5, 6}));
        assertEquals(2, findPivot(new int[] {6, 7, 0, 1, 2, 3, 4, 5}));
    }


    @Test
    void test() {
        assertEquals(4, search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
    }
}
