package techiedelight.medium;
/*

Given a maze in the form of a binary rectangular matrix, find the length of the shortest path from a given source to a given destination. The path can only be constructed out of cells having value 1, and at any moment, you can only move one step in one of the four directions (Top, Left, Down, Right).

Input:

matrix = [
	[1, 1, 1, 1, 1, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, 1, 1, 0, 1, 0, 1],
	[0, 0, 1, 0, 1, 1, 1, 0, 0, 1],
	[1, 0, 1, 1, 1, 0, 1, 1, 0, 1],
	[0, 0, 0, 1, 0, 0, 0, 1, 0, 1],
	[1, 0, 1, 1, 1, 0, 0, 1, 1, 0],
	[0, 0, 0, 0, 1, 0, 0, 1, 0, 1],
	[0, 1, 1, 1, 1, 1, 1, 1, 0, 0],
	[1, 1, 1, 1, 1, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, 0, 1, 1, 0, 0, 1]
]
src  = (0, 0)
dest = (5, 7)

Output: 12

Explanation: The shortest path from (0, 0) to (5, 7) has length 12. The shortest path is marked below with x.

[
	[x, x, x, x, x, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, x, 1, 0, 1, 0, 1],
	[0, 0, 1, 0, x, x, x, 0, 0, 1],
	[1, 0, 1, 1, 1, 0, x, x, 0, 1],
	[0, 0, 0, 1, 0, 0, 0, x, 0, 1],
	[1, 0, 1, 1, 1, 0, 0, x, 1, 0],
	[0, 0, 0, 0, 1, 0, 0, 1, 0, 1],
	[0, 1, 1, 1, 1, 1, 1, 1, 0, 0],
	[1, 1, 1, 1, 1, 0, 0, 1, 1, 1],
	[0, 0, 1, 0, 0, 1, 1, 0, 0, 1]
]

Note: The solution should return -1 if no path is possible.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import techiedelight.Pair;

class ShortestPath {
    public static int findShortestPath(int[][] mat, Pair<Integer,Integer> src, Pair<Integer,Integer> dest) {
//        return findShortestPathDFS(mat, new HashMap<>(), src, dest, 0);
        return findShortestPathBFS(mat, src, dest);
    }

    // breadth-first-search
    public static int findShortestPathBFS(int[][] mat, Pair<Integer, Integer> src, Pair<Integer, Integer> dest) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Check if source or destination is not valid
        if (src.first < 0 || src.first >= rows || src.second < 0 || src.second >= cols ||
                dest.first < 0 || dest.first >= rows || dest.second < 0 || dest.second >= cols ||
                mat[src.first][src.second] == 0 || mat[dest.first][dest.second] == 0) {
            return -1;
        }

        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // Up, Left, Down, Right

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(src);

        int[][] distances = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        distances[src.first][src.second] = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currentPos = queue.poll();

            for (int[] dir : directions) {
                int newRow = currentPos.first + dir[0];
                int newCol = currentPos.second + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && mat[newRow][newCol] == 1) {
                    int newDist = distances[currentPos.first][currentPos.second] + 1;
                    if (newDist < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newDist;
                        if (newRow == dest.first && newCol == dest.second) {
                            queue.clear();
                            break;
                        } else {
                            queue.offer(Pair.of(newRow, newCol));
                        }
                    }
                }
            }
        }

        int shortestPath = distances[dest.first][dest.second];

        return (shortestPath == Integer.MAX_VALUE) ? -1 : shortestPath;
    }

    // depth-first-search
    public static int findShortestPathDFS(int[][] mat,
                                       Map<Pair<Integer,Integer>,Integer> visited,
                                       Pair<Integer,Integer> src,
                                       Pair<Integer,Integer> dest,
                                       int len) {
        if (mat[src.first][src.second] == 0 ) {
            return -1;
        }
        if (src.equals(dest)) {
            return len;
        }
        Integer currentDist = visited.get(src);
        if (currentDist == null || currentDist > len) {
            visited.put(src, len);
        } else {
            return -1;
        }

        int res = -1;

        // row
        if (src.first > 0) {
            Pair<Integer,Integer> to = Pair.of(src.first - 1, src.second);
            int dist = findShortestPathDFS(mat, visited, to, dest, len + 1);
            if (dist >= 0 && (res == -1 || dist < res)) {
                res = dist;
            }
        }
        if (src.first < mat.length - 1) {
            Pair<Integer,Integer> to = Pair.of(src.first + 1, src.second);
            int dist = findShortestPathDFS(mat, visited, to, dest, len + 1);
            if (dist >= 0 && (res == -1 || dist < res)) {
                res = dist;
            }
        }
        // col
        if (src.second > 0) {
            Pair<Integer,Integer> to = Pair.of(src.first, src.second - 1);
            int dist = findShortestPathDFS(mat, visited, to, dest, len + 1);
            if (dist >= 0 && (res == -1 || dist < res)) {
                res = dist;
            }
        }
        if (src.second < mat[0].length - 1) {
            Pair<Integer,Integer> to = Pair.of(src.first, src.second + 1);
            int dist = findShortestPathDFS(mat, visited, to, dest, len + 1);
            if (dist >= 0 && (res == -1 || dist < res)) {
                res = dist;
            }
        }

        return res;
    }

    @Test
    void test() {
        int[][] mat = new int[][] {
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 1, 0, 0, 1}
        };
        assertEquals(12, findShortestPath(mat, Pair.of(0, 0), Pair.of(5, 7)));
    }

    @Test
    void test2() {
        int[][] mat = new int[][] {
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 1, 0, 0, 1}

        };
        assertEquals(-1, findShortestPath(mat, Pair.of(0, 0), Pair.of(9, 8))); // (row, col)
    }
}
