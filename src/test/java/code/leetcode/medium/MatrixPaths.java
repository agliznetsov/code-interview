package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MatrixPaths {
    public int uniquePaths(int m, int n) {
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    mat[i][j] = 1;
                } else {
                    mat[i][j] = mat[i - 1][j] + mat[i][j - 1];
                }
            }
        }
        return mat[m - 1][n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }
        for (int j = 1; j < n; j++) {
            grid[0][j] = grid[0][j] + grid[0][j-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    // Function to calculate the unique paths in a grid with obstacles
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Get the dimensions of the grid
        int numRows = obstacleGrid.length;
        int numCols = obstacleGrid[0].length;

        // Initialize a DP table with dimensions equivalent to the obstacle grid
        int[][] dp = new int[numRows][numCols];

        // Set up the first column of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int row = 0; row < numRows && obstacleGrid[row][0] == 0; ++row) {
            dp[row][0] = 1;
        }

        // Set up the first row of the DP table. If there is an obstacle,
        // paths beyond that point are not possible, so the loop will break.
        for (int col = 0; col < numCols && obstacleGrid[0][col] == 0; ++col) {
            dp[0][col] = 1;
        }

        // Iterate over the grid starting from cell (1, 1) to calculate the
        // number of unique paths to each cell, considering the obstacles.
        for (int row = 1; row < numRows; ++row) {
            for (int col = 1; col < numCols; ++col) {
                // If the current cell is not an obstacle
                if (obstacleGrid[row][col] == 0) {
                    // Number of paths to current cell is the sum of paths to the
                    // cell above it and the cell to the left of it.
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                }
                // If the current cell is an obstacle, dp[row][col] remains 0
            }
        }

        // Return the number of unique paths to the bottom-right corner of the grid
        return dp[numRows - 1][numCols - 1];
    }

    @Test
    void test() {
        assertEquals(28, uniquePaths(3, 7));
    }

    @Test
    void test2() {
        assertEquals(0, uniquePathsWithObstacles(new int[][] {{0, 0}, {1, 1}, {0, 0}}));
    }

    @Test
    void test3() {
        assertEquals(7, minPathSum(new int[][]
                {{1,3,1},
                 {1,5,1},
                 {4,2,1}}));
    }
}
