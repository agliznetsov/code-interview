package code.techiedelight.medium;
/*

Given the root of a binary tree and two tree nodes, x and y, return the lowest common ancestor (LCA) of x and y in the binary tree.

The lowest common ancestor (LCA) of two nodes x and y in a binary tree is the lowest (i.e., deepest) node that has both x and y as descendants, where each node can be a descendant of itself (so if x is reachable from w, w is the LCA). In other words, the LCA of x and y is the shared ancestor of x and y that is located farthest from the root.

For example, consider the following binary tree.

		   1
		 /   \
		/	  \
	   2	   3
	   \	  / \
		\	 /	 \
		 4	5	  6
		   / \
		  /   \
		 7	   8

Input: x = Node 6, y = Node 7
Output: Node 3
Explanation: The common ancestors of nodes 6 and 7 are 1 and 3. Out of nodes 1 and 3, the LCA is 3 as it is farthest from the root.

Input: x = Node 5, y = Node 8
Output: Node 5
Explanation: Node 8 itself is descendant of node 5 (and node 5 can be a descendant of itself).

Input: x = Node 2, y = Node 5
Output: Node 1

Note: The solution should return null if either x or y is not the actual node in the tree.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class LCA {
    private static class Node {
        int data;            // data field
        Node left, right;    // pointer to the left and right child

        Node() {
        }

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    public static Node findLCA(Node root, Node x, Node y) {
        Browser browser = new Browser();
        browser.browse(root, x, y, new ArrayList<>());
        return browser.findLCA(x, y);
    }

    private static class Browser {
        public List<Node> xParents;
        public List<Node> yParents;

        public void browse(Node node, Node x, Node y, List<Node> parents) {
            if (node == x) {
                xParents = add(parents, node);
            }
            if (node == y) {
                yParents = add(parents, node);
            }
            if (node.left != null) {
                browse(node.left, x, y, add(parents, node));
            }
            if (node.right != null) {
                browse(node.right, x, y, add(parents, node));
            }
        }

        private List<Node> add(List<Node> list, Node node) {
            List<Node> res = new ArrayList<>(list);
            res.add(node);
            return res;
        }

        public Node findLCA(Node x, Node y) {
            if (xParents == null || yParents == null) {
                return null;
            }
            // 1,3,6
            // 1,3,5,7
            int i = 0;
            while (i < xParents.size() && i < yParents.size() && xParents.get(i) == yParents.get(i)) {
                i++;
            }
            return xParents.get(i - 1);
        }
    }

    @Test
    void test() {
        /* Construct the following tree
              1
            /   \
           /     \
          2       3
           \     / \
            4   5   6
               / \
              7   8
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);

        Node node = findLCA(root, root.right.left.left, root.right.right);
        assertEquals(root.right, node);

        node = findLCA(root, root.right, root.right.right);
        assertEquals(root.right, node);
    }
}

