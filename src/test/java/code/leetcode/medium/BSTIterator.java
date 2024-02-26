package code.leetcode.medium;


import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import code.utils.TreeNode;

class BStIteratorTest {

    class BSTIterator {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current;

        public BSTIterator(TreeNode root) {
            this.current = root;
        }

        public int next() {
            while (current != null || !stack.isEmpty()) {
                // Traverse to the leftmost node
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                // Visit the top node and move to its right subtree
                current = stack.pop();
                int val = current.val;
                current = current.right;
                return val;
            }
            return -1;
        }

        public boolean hasNext() {
            return current != null || !stack.isEmpty();
        }
    }


    @Test
    void test() {
        var root = new TreeNode(7);
        var n3 = new TreeNode(3);
        var n15 = new TreeNode(15);
        var n10 = new TreeNode(10);
        var n20 = new TreeNode(20);
        n15.left = n10;
        n15.right = n20;
        root.left = n3;
        root.right = n15;

        var iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val > 0) {
                System.out.println(val);
            } else {
                break;
            }
        }
    }
}
