package techiedelight.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import techiedelight.Node;

/*

Given the root of a binary tree, return the level order traversal of its nodes' values. The solution should consider the binary tree nodes level by level from left to right, i.e., process all nodes of level 1 first, followed by all nodes of level 2, and so on.

Input:
		   1
		 /   \
		/	  \
	   2	   3
	  /		  / \
	 /	  	 /	 \
	4		5	  6
		   / \
		  /   \
		 7	   8

Output: [1, 2, 3, 4, 5, 6, 7, 8]

*/
public class LevelOrderTraversal {
    public static List<Integer> findLevelOrderTraversal(Node<Integer> root)
    {
        if (root == null) {
            return List.of();
        }

        List<Integer> res = new ArrayList<>();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        Node curr;

        while (!queue.isEmpty())
        {
            curr = queue.poll();

            System.out.print(curr.data + " ");
            res.add((Integer) curr.data);

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }

        return res;
    }

    @Test
    void test()
    {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

       assertEquals(List.of(1,2,3,4,5,6,7), findLevelOrderTraversal(root));
    }
}
