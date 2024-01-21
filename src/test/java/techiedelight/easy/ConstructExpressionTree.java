package techiedelight.easy;

/*

Given a postfix expression, construct a binary expression tree from it and return its root. The binary expression tree is a binary tree whose leaves are operands, such as constants or variable names, and the other nodes contain operators.

Input: ab+cde+**
Output: Root of the following expression tree.

			 *
		   /   \
		 /		 \
		+		  *
	   / \		 / \
	  /	  \		/	\
	 a	   b   c	 +
					/ \
				   /   \
				  d		e

*/

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import techiedelight.Node;

class ConstructExpressionTree {

    public static Node constructExpressionTree(String postfix) {
        if (postfix == null || postfix.isEmpty()) {
            return null;
        }

        Stack<Node> ops = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (ch == '+' || ch == '*' || ch == '-' || ch == '/') {
                Node res = new Node(ch);
                res.right = ops.pop();
                res.left = ops.pop();
                ops.push(res);
            } else {
                ops.push(new Node(ch));
            }
        }

        return ops.pop();
    }

    @Test
    void test() {
        assertNotNull(constructExpressionTree("ab+cde+**"));
    }
}
