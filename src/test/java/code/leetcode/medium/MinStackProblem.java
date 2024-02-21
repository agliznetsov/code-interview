package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

public class MinStackProblem {
    static class MinStack {
        Deque<Node> deque = new ArrayDeque<>();

        public MinStack() {
        }

        public void push(int val) {
            int min = deque.isEmpty() ? val : Math.min(val, getMin());
            deque.push(new Node(val, min));
        }

        public void pop() {
            deque.pop();
        }

        public int top() {
            return deque.peek().val;
        }

        public int getMin() {
            return deque.peek().min;
        }

        private static class Node {
            int val;
            int min;

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }
    }

    MinStack stack = new MinStack();

    @Test
    void test() {
        stack.push(3);
        stack.push(5);
        stack.push(1);
        stack.push(10);

        assertEquals(10, stack.top());
        assertEquals(1, stack.getMin());
    }
}
