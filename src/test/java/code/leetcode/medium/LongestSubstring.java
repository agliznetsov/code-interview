package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int start = 0;
        int length = 0;
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            var prevIndex = map.get(ch);
            if (prevIndex != null && prevIndex >= start) {
                length = Math.max(length, i - start);
                start = prevIndex + 1;
            }
            map.put(ch, i);
        }
        length = Math.max(length, s.length() - start);
        return length;
    }

    @Test
    void test() {
        assertEquals(2, lengthOfLongestSubstring("abba"));
        assertEquals(3, lengthOfLongestSubstring("abc"));
        assertEquals(7, lengthOfLongestSubstring("abcdaxyz"));
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }
}
