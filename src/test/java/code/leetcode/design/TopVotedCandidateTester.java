package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TopVotedCandidateTester {

    class TopVotedCandidate {
        List<int[]> results = new ArrayList<>();

        public TopVotedCandidate(int[] persons, int[] times) {
            int leader = 0;
            int maxVotes = 0;
            HashMap<Integer,Integer> votes = new HashMap<>();
            for (int i = 0; i < persons.length; i++) {
                int count = votes.computeIfAbsent(persons[i], it -> 0) + 1;
                votes.put(persons[i], count);
                if (count >= maxVotes) {
                    maxVotes = count;
                    results.add(new int[] {times[i], persons[i]});
                }
            }
        }

        public int q(int target) {
            // binary search
            int left = 0;
            int right = results.size() - 1;
            int index = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (results.get(mid)[0] <= target) {
                    index = mid;
                    left = mid + 1; // Move to the right half
                } else {
                    right = mid - 1; // Move to the left half
                }
            }
            return results.get(index)[1]; // Return the closest value less than or equal to the target
        }

    }

    @Test
    void test() {
        TopVotedCandidate top = new TopVotedCandidate(
                new int[]{0, 1, 1, 0, 0, 1, 0},
                new int[]{0, 5, 10, 15, 20, 25, 30});
        assertEquals(0, top.q(3));
        assertEquals(1, top.q(12));
        assertEquals(1, top.q(25));
        assertEquals(0, top.q(15));
        assertEquals(0, top.q(24));
        assertEquals(1, top.q(8));
    }
}
