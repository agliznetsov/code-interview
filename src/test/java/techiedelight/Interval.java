package techiedelight;

import java.util.Objects;

public class Interval {
    public int begin;
    public int end;

    public static Interval of(int begin, int end) {
        return new Interval(begin, end);
    }

    public Interval(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Interval interval = (Interval) o;
        return begin == interval.begin && end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }

    @Override
    public String toString() {
        return "{" +
                begin +
                ", " + end +
                '}';
    }
}
