package leetcode.medium;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            List<Integer> next = triangle.get(i + 1);
            for (int n = 0; n < row.size(); n++) {
                row.set(n, row.get(n) + Math.min(next.get(n), next.get(n + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    @Test
    void test() {
        assertEquals(11, minimumTotal(asList(asList(2), asList(3, 4), asList(6, 5, 7), asList(4, 1, 8, 3))));
    }
}
