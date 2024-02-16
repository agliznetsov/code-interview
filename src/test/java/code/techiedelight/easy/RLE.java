package code.techiedelight.easy;
/*

Run–length encoding (RLE) is a simple form of lossless data compression that runs on sequences with the same value occurring many consecutive times. It encodes the sequence to store only a single value and its count.

Input: "WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW"
Output: "12W1B12W3B24W1B14W"
Explanation: String can be interpreted as a sequence of twelve W’s, one B, twelve W’s, three B’s, and so on..

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RLE {
    public static String encode(String s) {
        StringBuilder sb = new StringBuilder();
        Character lastCh = null;
        int counter = 0;

        for (char ch : s.toCharArray()) {
            if (lastCh == null) {
                lastCh = ch;
                counter = 1;
            } else if (ch == lastCh) {
                counter++;
            } else {
                sb.append(counter);
                sb.append(lastCh);
                lastCh = ch;
                counter = 1;
            }
        }

        if (lastCh != null) {
            sb.append(counter);
            sb.append(lastCh);
        }

        return sb.toString();
    }

    @Test
    void test1() {
        assertEquals("12W1B12W3B24W1B14W",
                encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW"));
    }

}
