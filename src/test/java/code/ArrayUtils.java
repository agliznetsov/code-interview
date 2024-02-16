package code;

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
}
