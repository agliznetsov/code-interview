package techiedelight;

import java.util.Objects;

public class Pair<U, V> {
    U a;
    V b;

    public Pair(U a, V b) {
        this.a = a;
        this.b = b;
    }

    public static <U, V> Pair<U, V> of(U a, V b) {
        return new Pair<>(a, b);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
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
        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
