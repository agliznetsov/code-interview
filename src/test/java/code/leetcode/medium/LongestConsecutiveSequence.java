package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import code.ArrayUtils;

class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer,Seq> begins = new HashMap<>();
        HashMap<Integer,Seq> ends = new HashMap<>();
        for (int num : nums) {
            if (visited.contains(num)) {
                continue;
            }
            visited.add(num);
            int end = num - 1;
            int begin = num + 1;
            Seq right = begins.get(begin);
            Seq left = ends.get(end);
            if (left != null && right != null) {
                ends.remove(end);
                begins.remove(begin);
                // merge
                Seq seq = new Seq(left.begin, right.end);
                begins.put(seq.begin, seq);
                ends.put(seq.end, seq);
            } else if (left != null) {
                ends.remove(left.end);
                left.end = num;
                ends.put(left.end, left);
            } else if (right != null) {
                begins.remove(right.begin);
                right.begin = num;
                begins.put(right.begin, right);
            } else {
                Seq seq = new Seq(num);
                begins.put(seq.begin, seq);
                ends.put(seq.end, seq);
            }
        }

        return begins.values().stream().mapToInt(it -> it.end - it.begin + 1).max().getAsInt();
    }

    private class Seq {
        int begin;
        int end;

        public Seq(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        public Seq(int num) {
            begin = num;
            end = num;
        }
    }

    @Test
    void test() {
        assertEquals(4, longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }

    @Test
    void test2() {
        assertEquals(9, longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    void test3() {
        assertEquals(4, longestConsecutive(new int[] {-7,-1,3,-9,-4,7,-3,2,4,9,4,-9,8,-7,5,-1,-7}));
    }
}
