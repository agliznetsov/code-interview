package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

public class BasicCalculator2 {
    public int calculate(String string) {
        return new Parser().parse(string);
    }

    private static class Parser {
        Deque<Integer> arguments = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        String number = "";

        int parse(String input) {
            for (var ch : input.toCharArray()) {
                if (Character.isDigit(ch)) {
                    number += ch;
                } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    handleArgument();
                    ops.push(ch);
                }
            }
            if (!number.isEmpty()) {
               handleArgument();
            }
//            System.out.println("");
            while (!ops.isEmpty()) {
                var op = ops.removeLast();
                var left = arguments.removeLast();
                var right = arguments.removeLast();
                var value = apply(left, right, op);
                arguments.addLast(value);
//                System.out.println(left + " " + op + " " + right + " = " + value);
            }
            return arguments.pop();
        }

        private void handleArgument() {
            arguments.push(Integer.parseInt(number));
            number = "";
            Character prevOp = ops.peek();
            if (prevOp != null && (prevOp == '*' || prevOp == '/')) {
                apply(ops.pop());
            }
        }

        private void apply(char op) {
            var right = arguments.pop();
            var left = arguments.pop();
            var value = apply(left, right, op);
            arguments.push(value);
//            System.out.println(left + " " + op + " " + right + " = " + value);
        }

        private int apply(int left, int right, char ch) {
            switch (ch) {
                case '+': return left + right;
                case '-': return left - right;
                case '*': return left * right;
                case '/': return left / right;
            };
            return 0;
        }
    }

    @Test
    void test() {
        assertEquals(123, calculate("123"));
        assertEquals(25, calculate("10 + 15"));
        assertEquals(6, calculate("1 + 2 + 3"));
        assertEquals(6, calculate("2 * 3"));
        assertEquals(16, calculate("10+2*3"));
        assertEquals(23, calculate("10*2+3"));
        assertEquals(15, calculate("1+2+3+4+5"));
        assertEquals(120, calculate("1*2*3*4*5"));
        assertEquals(-24, calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
