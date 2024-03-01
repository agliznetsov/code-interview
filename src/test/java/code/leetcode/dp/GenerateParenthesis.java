package code.leetcode.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GenerateParenthesis {

    List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        handle(n, 0, "");
        return list;
    }

    private void handle(int n, int openCount, String str) {
        if (n > 0) {
            handle(n - 1, openCount + 1, str + "(");
        }
        if (openCount > 0) {
            handle(n, openCount - 1, str + ")");
        }
        if (n == 0 && openCount == 0) {
            list.add(str);
        }
    }

    @Test
    void test1() {
        assertEquals(List.of("()"), generateParenthesis(1));
    }

    @Test
    void test2() {
        assertEquals(List.of("(())", "()()"), generateParenthesis(2));
    }

    @Test
    void test3() {
        assertEquals(List.of("((()))","(()())","(())()","()(())","()()()"), generateParenthesis(3));
    }
}
