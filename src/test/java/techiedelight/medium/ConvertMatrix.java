package techiedelight.medium;
/*

Give an `M × N` binary matrix, change all elements of row `i` and column `j` to 0 if cell (i, j) has value 0.

Input:
[
	[1, 1, 0, 1, 1],
	[1, 1, 1, 1, 1],
	[1, 1, 1, 0, 1],
	[1, 1, 1, 1, 1],
	[0, 1, 1, 1, 1]
]

Output:
[
	[0, 0, 0, 0, 0],
	[0, 1, 0, 0, 1],
	[0, 0, 0, 0, 0],
	[0, 1, 0, 0, 1],
	[0, 0, 0, 0, 0]
]

Explanation:

0’s are present at (0, 2), (4, 0), and (2, 3) in the input matrix. Therefore, every element of the following cells is changed to 0:

• row 0 and column 2
• row 4 and column 0
• row 2 and column 3

*/

import static techiedelight.ArrayUtils.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ConvertMatrix {
    public static void convertMatrix(int[][] mat) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int row : rows) {
            for (int i = 0; i < mat[row].length; i++) {
                mat[row][i] = 0;
            }
        }

        for (int col : cols) {
            for (int i = 0; i < mat.length; i++) {
                mat[i][col] = 0;
            }
        }
    }

    @Test
    void test() {
        int[][] mat = new int[][] {
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };
        print(mat);
        convertMatrix(mat);
        print(mat);
    }
}
