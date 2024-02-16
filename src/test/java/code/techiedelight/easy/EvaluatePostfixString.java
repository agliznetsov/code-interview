package code.techiedelight.easy;

/*

Given a postfix expression, efficiently evaluate it. Assume that the postfix expression contains only single-digit numeric operands, without any whitespace.

Input: "82/"
Output: 4
Explanation: 82/ will evaluate to 4 (8/2)

Input: "138*+"
Output: 4
Explanation: 138*+ will evaluate to 25 (1+8*3)

Input: "545*+5/"
Output: 5
Explanation: 545*+5/ will evaluate to 5 ((5+4*5)/5)


Assume valid postfix expression.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

class EvaluatePostfixString {
    public static int evalPostfix(String s) {
        Stack<Integer> ops = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                ops.push(Integer.parseInt(String.valueOf(ch)));
            } else {
                int res = 0;
                int op2 = ops.pop();
                int op1 = ops.pop();
                switch (ch) {
                    case '+':
                        res = op1 + op2;
                        break;
                    case '*':
                        res = op1 * op2;
                        break;
                    case '-':
                        res = op1 - op2;
                        break;
                    case '/':
                        res = op1 / op2;
                        break;
                }
                ops.push(res);
            }
        }

        return ops.pop();
    }

    @Test
    void test1() {
        assertEquals(25, evalPostfix("138*+"));
    }
}
