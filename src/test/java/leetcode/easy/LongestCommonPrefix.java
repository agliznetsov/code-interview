package leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int i = 0;
        boolean eq = false;
        do {
            eq = true;
            for (String s : strs) {
                if (s.length() == i || s.charAt(i) != strs[0].charAt(i)) {
                    eq = false;
                    break;
                }
            }
            if (eq) {
                i++;
            }
        } while (eq);
        if (i > 0) {
            return strs[0].substring(0, i);
        }
        return "";
    }

    @Test
    void test() {
        assertEquals("fl", longestCommonPrefix(new String[] {"flower","flow","flight"}));
    }
}
