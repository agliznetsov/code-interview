package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class InterleavingString {

    String s1;
    String s2;
    String s3;
    Map<Long, Boolean> cache = new HashMap<>();
    long hit;
    long miss;

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (s3.length() == s1.length() + s2.length()) {
            boolean res = checkString(0, 0);
            System.out.println("hit " + hit + "  miss " + miss);
            return res;
        }
        return false;
    }

    private boolean checkString(int p1, int p2) {
        int n = p1 + p2;
        if (n == s3.length()) {
            return true;
        }

        long key = (long) p1 << 32 | p2;
        Boolean res = cache.get(key);
        if (res == null) {
            miss++;
            res = false;
            if (p1 < s1.length() && s1.charAt(p1) == s3.charAt(n)) {
                if (checkString(p1 + 1, p2)) {
                    res = true;
                }
            }
            if (!res) {
                if (p2 < s2.length() && s2.charAt(p2) == s3.charAt(n)) {
                    res = checkString(p1, p2 + 1);
                }
            }
            cache.put(key, res);
        } else {
            hit++;
        }
        return res;
    }


    @Test
    void test() {
        assertTrue(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    @Test
    void test2() {
        isInterleave(
                "abababababababababababababababababababababababababababababababababababababababababababababababababbb",
                "babababababababababababababababababababababababababababababababababababababababababababababababaaaba",
                "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababbb"
        );
    }
}
