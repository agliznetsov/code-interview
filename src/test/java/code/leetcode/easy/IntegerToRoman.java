package code.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IntegerToRoman {

    int[] decimals = new int[]      {1,  4,  5,  9,   10, 40,  50, 90, 100, 400, 500, 900, 1000};
    String[] romans = new String[] {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};

    public int romanToInt(String s) {
        int num = 0;
        int i = decimals.length - 1;
        String rom = romans[i];
        int dec = decimals[i];
        while (s.length() > 0)   {
            while (!s.startsWith(rom)) {
                i--;
                rom = romans[i];
                dec = decimals[i];
            }
            num += dec;
            s = s.substring(rom.length());
        }
        return num;
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int i = decimals.length - 1;
        String rom = romans[i];
        int dec = decimals[i];
        while (num > 0)   {
            while (num < dec) {
                i--;
                rom = romans[i];
                dec = decimals[i];
            }
            sb.append(rom);
            num -= dec;
        }
        return sb.toString();
    }

    @Test
    void test() {
        assertEquals("III", intToRoman(3));
    }

    @Test
    void test2() {
        assertEquals("LVIII", intToRoman(58));
    }

    @Test
    void test3() {
        assertEquals("MCMXCIV", intToRoman(1994));
    }

    @Test
    void test4() {
        assertEquals(3, romanToInt("III"));
    }

    @Test
    void test5() {
        assertEquals(58, romanToInt("LVIII"));
    }

    @Test
    void test6() {
        assertEquals(1994, romanToInt("MCMXCIV"));
    }
}
