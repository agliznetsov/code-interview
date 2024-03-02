package code.leetcode.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class Parentheses {

    private HashMap<String,List<Integer>> cache = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        return evaluate(expression);
    }

    private List<Integer> evaluate(String expression) {
        List<Integer> values = cache.get(expression);
        if (values == null) {
            values = new ArrayList<>();
            for (int i = 0; i < expression.length(); i++) {
                if (!Character.isDigit(expression.charAt(i))) {
                    char op = expression.charAt(i);
                    List<Integer> left = evaluate(expression.substring(0, i));
                    List<Integer> right = evaluate(expression.substring(i + 1));
                    for (int val1 : left) {
                        for (int val2 : right) {
                            values.add(evaluate(val1, val2, op));
                        }
                    }
                }
            }
            if (values.isEmpty()) {
                values.add(Integer.parseInt(expression));
            }
            cache.put(expression, values);
        }
        return values;
    }

    private Integer evaluate(int val1, int val2, char op) {
        switch (op) {
            case '-':
                return val1 - val2;
            case '+':
                return val1 + val2;
            case '*':
                return val1 * val2;
        }
        return 0;
    }

    @Test
    void test() {
        assertEquals(List.of(2, 0), diffWaysToCompute("2-1-1"));
    }

    @Test
    void test2() {
        assertEquals(List.of(-34, -10, -14, -10, 10), diffWaysToCompute("2*3-4*5"));
    }
}
