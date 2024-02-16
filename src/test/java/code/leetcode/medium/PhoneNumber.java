package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PhoneNumber {

    Map<Character, List<Character>> map = Map.of(
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z')
    );

    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        addChars(digits, "");
        return list;
    }

    private void addChars(String digits, String s) {
        if (digits.isEmpty()) {
            if (!s.isEmpty()) {
                list.add(s);
            }
            return;
        }

        char digit = digits.charAt(0);
        String leftDigits = digits.substring(1);
        for (char letter : map.get(digit)) {
            addChars(leftDigits, s + letter);
        }
    }

    @Test
    void test() {
        assertEquals(List.of("a","b","c"), letterCombinations("2"));
    }

    @Test
    void test2() {
        assertEquals(List.of("ad","ae","af","bd","be","bf","cd","ce","cf"), letterCombinations("23"));
    }
}
