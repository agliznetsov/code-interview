package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringCompression3 {
    public String compressedString(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        char ch = word.charAt(0);
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (ch == word.charAt(i) && counter < 9) {
                counter++;
            } else {
                sb.append(counter);
                sb.append(ch);
                counter = 1;
                ch = word.charAt(i);
            }
        }
        if (counter > 0) {
            sb.append(counter);
            sb.append(ch);
        }
        return sb.toString();
    }

    @Test
    void test() {
        assertEquals("3a2b", compressedString("aaabb"));
    }
}
