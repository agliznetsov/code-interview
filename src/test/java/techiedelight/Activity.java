package techiedelight;

import java.util.Objects;

public class Activity {
    final int start;
    final int finish;

    public static Activity a(int s, int f) {
        return new Activity(s, f);
    }

    public static Activity of(int s, int f) {
        return new Activity(s, f);
    }

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Activity activity = (Activity) o;
        return start == activity.start && finish == activity.finish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish);
    }

    @Override
    public String toString() {
        return "{" +
                start +
                ", " + finish +
                '}';
    }
}
