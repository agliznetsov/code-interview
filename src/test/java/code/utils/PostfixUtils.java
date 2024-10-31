package code.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

public class PostfixUtils {
    public static double evalPostfix(String s) {
        var ops = new Stack<Double>();
        var parts = s.trim().split(" ");
        for (var part : parts) {
            if (part.equals("+") || part.equals("-") || part.equals("*") || part.equals("/")) {
                double res = 0;
                var op2 = ops.pop();
                var op1 = ops.pop();
                switch (part) {
                    case "+":
                        res = op1 + op2;
                        break;
                    case "*":
                        res = op1 * op2;
                        break;
                    case "-":
                        res = op1 - op2;
                        break;
                    case "/":
                        res = op1 / op2;
                        break;
                }
                ops.push(res);
            } else if (!part.isEmpty()){
                ops.push(Double.parseDouble(part));
            }
        }
        return ops.pop();
    }

    @Test
    void test() {
        assertEquals(5.0, evalPostfix("2 3 +"));
        assertEquals(55.0, evalPostfix("  10    5.5    *  "));
    }
}
