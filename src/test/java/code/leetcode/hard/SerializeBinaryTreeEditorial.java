package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import code.utils.TreeNode;

public class SerializeBinaryTreeEditorial {

    public class Codec {
        // Constants to represent null values and the separator in the serialized string.
        private static final String NULL_SYMBOL = ".";
        private static final String SEPARATOR = " ";

        /**
         * Encodes a tree to a single string.
         *
         * @param root The root of the binary tree.
         * @return A string representing the pre-order traversal of the binary tree.
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            serializePreOrder(root, stringBuilder);
            return stringBuilder.toString();
        }

        /**
         * Helper method to serialize the tree using pre-order traversal.
         *
         * @param node The current node in the tree traversal.
         * @param stringBuilder The StringBuilder used to create the serialized string.
         */
        private void serializePreOrder(TreeNode node, StringBuilder stringBuilder) {
            if (node == null) {
                stringBuilder.append(NULL_SYMBOL).append(SEPARATOR);
                return;
            }
            stringBuilder.append(node.val).append(SEPARATOR);
            serializePreOrder(node.left, stringBuilder);
            serializePreOrder(node.right, stringBuilder);
        }

        /**
         * Decodes your encoded data to tree.
         *
         * @param data The serialized string representation of the binary tree.
         * @return The root node of the decoded binary tree.
         */
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            List<String> nodesList = new LinkedList<>(Arrays.asList(data.split(SEPARATOR)));
            return deserializePreOrder(nodesList);
        }

        /**
         * Helper method to deserialize the list of values into a binary tree
         * using pre-order traversal.
         *
         * @param nodesList The linked list of values representing the pre-order traversal.
         * @return The root node of the (sub)tree.
         */
        private TreeNode deserializePreOrder(List<String> nodesList) {
            String value = nodesList.remove(0);
            if (NULL_SYMBOL.equals(value)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(value));
            node.left = deserializePreOrder(nodesList);
            node.right = deserializePreOrder(nodesList);
            return node;
        }
    }


    @Test
    void test1() {
        var root = createTree();
        roundtrip(root);
    }

    @Test
    void test2() {
        var root = createTree();
        root.right.right.right = new TreeNode(6);

        roundtrip(root);
    }

    @Test
    void test3() {
        var root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.left.left = new TreeNode(-2);
        root.left.left.left = new TreeNode(-3);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(3);

        roundtrip(root);
    }

    @Test
    void test5() {
        var root = new TreeNode(0);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(3);
        root.right.right.right.right = new TreeNode(4);
        root.right.right.right.right.right = new TreeNode(5);

        roundtrip(root);
    }

    @Test
    void test4() {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        roundtrip(root);
    }

    @Test
    void test0() {
        var root = new TreeNode(-1);
        roundtrip(root);
    }

    private void roundtrip(TreeNode root) {
        var str1 = new Codec().serialize(root);
        System.out.println("[" + str1 + "]");

        var root2 = new Codec().deserialize(str1);
        var str2 = new Codec().serialize(root2);
        assertEquals(str1, str2);
    }

    private TreeNode createTree() {
        var root = new TreeNode(1);
        var n2 = new TreeNode(2);
        var n3 = new TreeNode(3);
        var n4 = new TreeNode(4);
        var n5 = new TreeNode(5);
        root.left = n2;
        root.right = n3;
        n3.left = n4;
        n3.right = n5;
        return root;
    }

}
