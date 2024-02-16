package code.techiedelight.easy;
/*

Given a positive integer n, return all n–digit binary numbers without any consecutive 1's.

Input: n = 5
Output: {"00000", "00001", "00010", "00100", "00101", "01000", "01001", "01010", "10000", "10001", "10010", "10100", "10101"}

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NDigitNumber7 {
    public static Set<String> findNDigitNumbers(int n) {
        Set<String> set = new HashSet<>();

        addDigit("", n, set);

        return set;
    }

    private static void addDigit(String str, int size, Set<String> set) {
        if (str.length() == size) {
            set.add(str);
        } else {
            addDigit(str + "0", size, set);
            if (!str.endsWith("1")) {
                addDigit(str + "1", size, set);
            }
        }
    }

    @Test
    void test() {
        assertEquals(13, findNDigitNumbers(5).size());
    }
}
