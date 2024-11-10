package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringCompression {

    public int compress(char[] chars) {
        if (chars.length < 2) {
            return chars.length;
        }
        char ch = chars[0];
        int counter = 0;
        int target = 0;
        for (int i = 0; i < chars.length; i++) {
            if (ch == chars[i]) {
                counter++;
            } else {
                target = write(chars, target, ch, counter);
                counter = 1;
                ch = chars[i];
            }
        }
        if (counter > 0) {
            target = write(chars, target, ch, counter);
        }
        return target;
    }

    private int write(char[] chars, int target, char ch, int counter) {
        chars[target] = ch;
        target++;
        if (counter > 1) {
            var str = String.valueOf(counter);
            for(var digit : str.toCharArray()) {
                chars[target] = digit;
                target++;
            }
        }
        return target;
    }

    @Test
    void test() {
        var chars = "aaabccc".toCharArray();
        assertEquals(5, compress(chars));
        assertEquals("a3bc3", new String(chars, 0, 5));
    }
}
