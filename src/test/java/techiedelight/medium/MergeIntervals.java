package techiedelight.medium;
/*

Given a set of intervals, return all non-overlapping intervals after merging the overlapping intervals.

Input : [(1, 5), (2, 3), (4, 6), (7, 8), (8, 10), (12, 15)]
Output: {(1, 6), (7, 10), (12, 15)}

*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import techiedelight.Interval;

class MergeIntervals {
    public static Set<Interval> mergeIntervals(List<Interval> intervals) {
		intervals = new LinkedList<>(intervals); // make list writable
        var set = new HashSet<Interval>();
        boolean merged = true;
        while (!intervals.isEmpty()) {
            merged = false;
            var a = intervals.get(0);
            intervals.remove(0);
            int i = 0;
            while (i < intervals.size()) {
                var b = intervals.get(i);
                if (intersect(a, b)) {
                    a = merge(a, b);
                    intervals.remove(i);
                    merged = true;
                } else {
                    i++;
                }
            }
            if (merged) {
                intervals.add(0, a);
            } else {
                set.add(a);
            }
        }
        return set;
    }

    private static Interval merge(Interval a, Interval b) {
        return Interval.of(Math.min(a.begin, b.begin), Math.max(a.end, b.end));
    }

    private static boolean intersect(Interval a, Interval b) {
        return a.begin <= b.end && a.end >= b.begin;
    }

    // Function to merge overlapping intervals
    public static Set<Interval> mergeIntervals2(List<Interval> intervals)
    {
        intervals = intervals.stream().sorted(Comparator.comparingInt(a -> a.begin)).collect(Collectors.toList());
        Stack<Interval> stack = new Stack<>();
        for (Interval curr: intervals)
        {
            // if the stack is empty or the top interval in the stack does not overlap
            // with the current interval, push it into the stack
            if (stack.empty() || curr.begin > stack.peek().end) {
                stack.push(curr);
            }

            // if the top interval of the stack overlaps with the current interval,
            // merge two intervals by updating the end of the top interval
            // to the current interval
            if (stack.peek().end < curr.end) {
                stack.peek().end = curr.end;
            }
        }
        return new HashSet<>(stack);
    }

    @Test
    void testIntersect() {
        assertTrue(intersect(Interval.of(1,3), Interval.of(0, 6)));
        assertTrue(intersect(Interval.of(0,6), Interval.of(1, 3)));
    }

    @Test
    void test() {
        var res = mergeIntervals2(List.of(
                Interval.of(1, 5), Interval.of(2, 3), Interval.of(4, 6), Interval.of(7, 8), Interval.of(8, 10),
                Interval.of(12, 15)
        ));
        System.out.println(res);
        assertEquals(3, res.size());
    }

    @Test
    void test2() {
        var res = mergeIntervals2(List.of(
                Interval.of(1, 3), Interval.of(4, 5), Interval.of(0, 6), Interval.of(7, 8)
        ));
        System.out.println(res);
        assertEquals(2, res.size());
    }

    //Failed for Input [(1, 3), (4, 5), (0, 6), (7, 8)]
    //Expected output —> [(7, 8), (0, 6)]
    //Your output     —> [(4, 5), (7, 8), (1, 3), (0, 6)]


}
