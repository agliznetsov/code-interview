package code.techiedelight.medium;
/*

Given a list of jobs where each job has a start and finish time, and a profit associated with it, find a maximum profit subset of non-overlapping jobs.

Input: jobs = [(0, 6, 60), (5, 9, 50), (1, 4, 30), (5, 7, 30), (3, 5, 10), (7, 8, 10)]		// (starting time, finishing time, associated profit)
Output: 80
Explanation: The jobs involved in the maximum profit are: (1, 4, 30) and (5, 9, 50).

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import code.techiedelight.Job;

class WeightedJobScheduling {
    public static int findMaxProfit(List<Job> jobs) {
        return new RecursiveSolver(jobs).findMaxProfit(0, -1);
    }

    private static class RecursiveSolver {
        private final List<Job> jobs;
        private final Map<Long,Integer> profits = new HashMap<>();
        public long hitCount = 0;
        public long misCount = 0;

        private RecursiveSolver(List<Job> jobs) {
            this.jobs = jobs.stream()
                    .sorted(Comparator.comparingInt(a -> a.start))
                    .collect(Collectors.toList());
        }

        public int findMaxProfit(int index, int endTime) {
            long key = (long) index << 16 | endTime;
            Integer profit = profits.get(key);
            if (profit == null) {
                misCount++;
                Job nextJob = null;
                for (int i = index; i < jobs.size(); i++) {
                    if (jobs.get(i).start >= endTime) {
                        nextJob = jobs.get(i);
                        break;
                    }
                }
                if (nextJob == null) {
                    return 0;
                }

                int take = nextJob.profit + findMaxProfit(index + 1, nextJob.finish);
                int skip = findMaxProfit(index + 1, endTime);
                profit = Math.max(take, skip);
                profits.put(key, profit);
            } else {
                hitCount++;
            }
            return profit;
        }
    }

    private static class TabularSolver {
        // Function to find the index of the last job which doesn't conflict with the
        // given job. It performs a linear search on the given list of jobs.
        private int findLastNonConflictingJob(List<Job> jobs, int n) {
            // find the last job index whose finish time is less than or equal to the
            // given job's start time
            for (int i = n - 1; i >= 0; i--) {
                if (jobs.get(i).finish <= jobs.get(n).start) {
                    return i;
                }
            }

            // return the negative index if no non-conflicting job is found
            return -1;
        }

        // Function to find the maximum profit of non-overlapping jobs using DP
        public int findMaxProfit(List<Job> jobs) {
            jobs = jobs.stream()
                    .sorted(Comparator.comparingInt(a -> a.finish))
                    .collect(Collectors.toList());

            // get the number of jobs
            int n = jobs.size();

            // base case
            if (n == 0) {
                return 0;
            }

            // construct a lookup table where the i'th index stores the maximum profit
            // for the first `i` jobs
            int[] maxProfit = new int[n];

            // maximum profit gained by including the first job
            maxProfit[0] = jobs.get(0).profit;

            // fill the `maxProfit[]` table in a bottom-up manner from the second index
            for (int i = 1; i < n; i++) {
                // find the index of the last non-conflicting job with the current job
                int index = findLastNonConflictingJob(jobs, i);

                // include the current job with its non-conflicting jobs
                int incl = jobs.get(i).profit;
                if (index != -1) {
                    incl += maxProfit[index];
                }

                // store the maximum profit by including or excluding the current job
                maxProfit[i] = Math.max(incl, maxProfit[i - 1]);
            }

            // return maximum profit
            return maxProfit[n - 1];
        }
    }

    List<Job> jobs = List.of(
            Job.of(0, 6, 60), Job.of(5, 9, 50), Job.of(1, 4, 30), Job.of(5, 7, 30), Job.of(3, 5, 10),
            Job.of(7, 8, 10));

    @Test
    void test() {
        assertEquals(80, findMaxProfit(jobs));
    }

    @Test
    void test2() {
        assertEquals(80, new TabularSolver().findMaxProfit(jobs));
    }

    @Test
    void benchmark() {
        Random random = new Random(0);
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            int start = random.nextInt(1000);
            int finish = start + random.nextInt(1000);
            int profit = random.nextInt(100);
            jobs.add(Job.of(start, finish, profit));
        }

        long start = System.currentTimeMillis();
        RecursiveSolver solver = new RecursiveSolver(jobs);
        solver.findMaxProfit(0, -1);
        System.out.println("hit " + solver.hitCount + " miss " + solver.misCount);
        System.out.println("recursive time " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        new TabularSolver().findMaxProfit(jobs);
        System.out.println("recursive time " + (System.currentTimeMillis() - start));
    }
}
