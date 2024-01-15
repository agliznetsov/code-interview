package techiedelight.easy;
/*

Given the root of a binary expression tree representing algebraic expressions, evaluate it and return its value. A binary expression tree is a binary tree, where the operators are stored in the tree's internal nodes, and the leaves contain constants.

Assume that each node of the binary expression tree has zero or two children. The supported operators are +(addition), −(subtraction), *(multiplication), ÷(division) and ^(exponentiation).

Input:

			(+)
		   /   \
		 /		 \
	   (*)		 (/)
	   / \		 / \
	  /	  \		/	\
	(-)	   5   21	 7
	/ \
   /   \
  10	5

Output: 28

Explanation: The corresponding infix notation is ((10-5)*5)+(21/7) = 28 which can be produced by traversing the expression tree in an inorder fashion.

*/

import org.junit.jupiter.api.Test;

import techiedelight.Node;

class EvaluateExpressionTree
{

	public static double evaluate(Node root)
	{
		if (root == null || root.data == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return Double.parseDouble(root.data);
		}
		double left = evaluate(root.left);
		double right = evaluate(root.right);
		return evaluateOp(root.data.charAt(0), left, right);
	}

	static double evaluateOp(char op, double left, double right) {
		switch (op) {
			case '+': return  left + right;
			case '-': return  left - right;
			case '*': return  left * right;
			case '/': return  left / right;
			case '^': return  Math.pow(left, right);
		}
		return 0;
	}

	@Test
	void test() {

	}
}
