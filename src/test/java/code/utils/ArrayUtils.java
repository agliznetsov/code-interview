package code.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {
    public static List<Integer> toList(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    public static void swap(int[] nums, int left, int right) {
        if (left != right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
    }

    public static void print(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (list.get(mid) == target)
                return mid;

            // If target is greater, ignore left half
            if (list.get(mid) < target)
                left = mid + 1;

                // If target is smaller, ignore right half
            else
                right = mid - 1;
        }

        // If the element is not present in the list
        return -1;
    }

    // Binary search method
    public static int closestValue(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int closest = Integer.MIN_VALUE; // Initialize closest to a minimum value

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the mid element is less than or equal to the target
            if (list.get(mid) <= target) {
                closest = Math.max(closest, list.get(mid)); // Update closest if current element is closer
                left = mid + 1; // Move to the right half
            } else {
                right = mid - 1; // Move to the left half
            }
        }
        return closest; // Return the closest value less than or equal to the target
    }
}
