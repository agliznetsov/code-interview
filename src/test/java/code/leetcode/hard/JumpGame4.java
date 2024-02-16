package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;

class JumpGame4 {

    private class Node {
        final int jumps;
        final int pos;

        private Node(int jumps, int pos) {
            this.jumps = jumps;
            this.pos = pos;
        }
    }

    public int minJumps(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }

        Queue<Node> queue = new ArrayDeque<>();
        Map<Integer,List<Integer>> values = new HashMap<>();
        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = values.computeIfAbsent(arr[i], it -> new ArrayList<>());
            if ((list.size() == 2 && list.get(list.size() - 1) == i - 1)
                    || (list.size() > 2 && list.get(list.size() - 1) == i - 1) && list.get(list.size() - 2) == i - 2)
            {
                list.set(list.size() - 1, i);
            } else {
                list.add(i);
            }
        }

        queue.add(new Node(0, 0));
        int counter = 0;
        visited[0] = true;
        System.out.println(values);
        while (!queue.isEmpty()) {
            counter++;
            Node node = queue.poll();
            if (node.pos == arr.length - 1) {
                System.out.println("my counter " + counter);
                return node.jumps;
            }

            visited[node.pos] = true;

            List<Integer> list = values.get(arr[node.pos]);
            if (list != null) {
                for (int i : list) {
                    if (i != node.pos && !visited[i]) {
                        queue.offer(new Node(node.jumps + 1, i));
                    }
                }
                values.remove(arr[node.pos]);
            }

            int left = node.pos - 1;
            int right = node.pos + 1;
            if (right < arr.length && !visited[right]) {
                queue.offer(new Node(node.jumps + 1, right));
            }
            if (left > 0 && !visited[left]) {
                queue.offer(new Node(node.jumps + 1, left));
            }
        }
        return -1;
    }

    public int minJumpsEditorial(int[] arr) {
        // Hash map to store indices of values in the array.
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int n = arr.length;

        // Populate the hash map with indices for each value.
        for (int i = 0; i < n; ++i) {
            indexMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // Queue for BFS, each element is a pair: [current index, current step count].
        Deque<int[]> queue = new LinkedList<>();
        // Set to keep track of visited indices.
        Set<Integer> visited = new HashSet<>();

        // Start BFS with the first index.
        visited.add(0);
        queue.offer(new int[] {0, 0});

        int counter = 0;
        System.out.println(indexMap);

        // BFS to find minimum steps.
        while (!queue.isEmpty()) {
            counter++;
            int[] element = queue.pollFirst();
            int currentIndex = element[0];
            int stepCount = element[1];
            // If we've reached the end of the array, return the step count.
            if (currentIndex == n - 1) {
                System.out.println("editorial counter " + counter);
                return stepCount;
            }

            // Increment step count for next potential moves.
            stepCount++;

            // Get all indices with the same value and add unseen ones to the queue.
            for (int index : indexMap.getOrDefault(arr[currentIndex], new ArrayList<>())) {
                if (!visited.contains(index)) {
                    visited.add(index);
                    queue.offer(new int[] {index, stepCount});
                }
            }

            // We remove this value from the map to prevent revisiting.
            indexMap.remove(arr[currentIndex]);

            // Check and add unseen next and previous indices to the queue.
            if (currentIndex + 1 < n && !visited.contains(currentIndex + 1)) {
                visited.add(currentIndex + 1);
                queue.offer(new int[] {currentIndex + 1, stepCount});
            }
            if (currentIndex - 1 >= 0 && !visited.contains(currentIndex - 1)) {
                visited.add(currentIndex - 1);
                queue.offer(new int[] {currentIndex - 1, stepCount});
            }
        }

        // If we've exhausted all options and haven't reached the end, return -1.
        return -1;
    }

    @Test
    void test() {
        assertEquals(0, minJumps(new int[] {7}));
    }

    @Test
    void test2() {
        assertEquals(1, minJumps(new int[] {7, 6, 9, 6, 9, 6, 9, 7}));
    }

    @Test
    void test3() {
        assertEquals(3, minJumps(new int[] {100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
    }

    @Test
    void test4() {
        assertEquals(2, minJumps(new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8}));
        assertEquals(2, minJumpsEditorial(new int[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8}));
    }

    @Test
    void test5() {
        assertEquals(3, minJumps(new int[] {7,7,2,1,7,7,7,3,4,1}));
    }

    @Test
    void test6() {
        assertEquals(4, minJumps(new int[] {1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,7,11}));
        System.out.println();
        assertEquals(4, minJumpsEditorial(new int[] {1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,7,11}));
    }
}
