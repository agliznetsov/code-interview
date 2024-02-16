package code.techiedelight;

import java.util.Objects;

public class Job {
    public final int start;
    public final int finish;
    public final int profit;

    public static Job of(int start, int finish, int profit) {
        return new Job(start, finish, profit);
    }

    public Job(int start, int finish, int profit) {
        this.start = start;
        this.finish = finish;
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return start == job.start && finish == job.finish && profit == job.profit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish, profit);
    }

    @Override
    public String toString() {
        return "Job{" +
                "start=" + start +
                ", finish=" + finish +
                ", profit=" + profit +
                '}';
    }
}
