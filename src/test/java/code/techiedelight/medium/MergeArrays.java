package code.techiedelight.medium;
/*

Given two sorted integer arrays, `X[]` and `Y[]` of size `m` and `n` each, in-place merge elements of `X[]` with elements of array `Y[]` by maintaining the sorted order, i.e., fill `X[]` with the first `m` smallest elements and fill `Y[]` with remaining elements.

Input :

X[] = [1, 4, 7, 8, 10]
Y[] = [2, 3, 9]

Output:

X[] = [1, 2, 3, 4, 7]
Y[] = [8, 9, 10]

*/

import static code.utils.ArrayUtils.swap;
import static code.utils.ArrayUtils.toList;

import org.junit.jupiter.api.Test;

class MergeArrays {
    public static void merge(int[] x, int[] y) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] > y[0]) {
                int tmp = x[i];
                x[i] = y[0];
                y[0] = tmp;

                for (int n = 1; n < y.length; n++) {
                    if (y[n - 1] > y[n]) {
                        swap(y, n - 1, n);
                    }
                }
            }
        }
    }

    @Test
    void test() {
        int[] x = new int[] {1, 4, 7, 8, 10};
        int[] y = new int[] {2, 3, 9};
        merge(x, y);
        System.out.println(toList(x));
        System.out.println(toList(y));
    }
}
