package techiedelight;

import java.util.Objects;

public class Pair<U, V> {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <U, V> Pair<U,V> of(U a, V b) {
        return new Pair<>(a, b);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "a=" + first +
                ", b=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?,?> pair = (Pair<?,?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
