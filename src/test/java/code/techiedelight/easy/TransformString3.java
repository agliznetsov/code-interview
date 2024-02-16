package code.techiedelight.easy;
/*

Given a string, remove all occurrences of "AB" and "C" in a single traversal of it.

Input: "CBAABCAB"
Output: "BA"

The solution should remove of all adjacent, as well as non-adjacent occurrences of string "AB" and "C".

Input: "ADAABCB"
Output: "AD"
Explanation: Removing the first adjacent occurrence of "AB" and "C" results in string "ADAB", which again needs to be processed for adjacent "AB". Therefore, the final output string will be "AD".

Input: "AACBBC"
Output: ""
Explanation: AACBBC -> AABB -> AB -> ""

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import org.junit.jupiter.api.Test;

class TransformString3 {
    public static String remove(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == 'C') {
                // remove
            } else if (ch == 'A') {
                stack.push(ch);
            } else if (ch == 'B') {
                if (!stack.isEmpty() && stack.peek() == 'A') {
                    stack.pop();
                } else {
                    sb.append(ch);
                }
            } else {
                var list = new ArrayList<>(stack);
                Collections.reverse(list);
                list.forEach(sb::append);
                stack.clear();
                sb.append(ch);
            }
        }

        var list = new ArrayList<>(stack);
        Collections.reverse(list);
        list.forEach(sb::append);

        return sb.toString();
    }

    @Test
    void test1() {
        assertEquals("BA", remove("CBAABCAB"));
    }

    @Test
    void test2() {
        assertEquals("AD", remove("ADAABCB"));
    }

}
