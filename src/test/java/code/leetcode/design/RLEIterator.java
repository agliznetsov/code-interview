package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RLEIteratorTest {
    class RLEIterator {
        int[] ranges;
        int[] values;
        int rangeIndex;
        int length = 0;

        public RLEIterator(int[] encoding) {
            ranges = new int[encoding.length / 2];
            values = new int[encoding.length / 2];
            for (int i = 0; i < ranges.length; i++) {
                if (encoding[i * 2] > 0) {
                    ranges[length] = encoding[i * 2];
                    values[length] = encoding[i * 2 + 1];
                    length++;
                }
            }
        }

        public int next(int n) {
            int value = -1;
            while (n > 0 && rangeIndex < length) {
                if (ranges[rangeIndex] <= n) {
                    n -= ranges[rangeIndex];
                    if (n == 0) {
                        value = values[rangeIndex];
                    }
                    rangeIndex++;
                } else {
                    ranges[rangeIndex] -= n;
                    n = 0;
                    value = values[rangeIndex];
                }
            }
            return value;
        }
    }

    @Test
    void test() {
        RLEIterator iterator = new RLEIterator(new int[] {3, 8, 0, 9, 2, 5});
        assertEquals(8, iterator.next(2));
        assertEquals(8, iterator.next(1));
        assertEquals(5, iterator.next(1));
        assertEquals(-1, iterator.next(2));
    }
}
