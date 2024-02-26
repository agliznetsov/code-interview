package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import code.utils.TreeNode;

public class SerializeBinaryTree3 {

    public class Codec {
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            return BFT(root);
        }

        private String BFT(TreeNode root) {
            LinkedList<Object[]> queue = new LinkedList<>();
            queue.add(new Object[] {root, 0});
            ArrayList<TreeNode> children = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            int row = 0;
            while (!queue.isEmpty()) {
                Object[] data = queue.poll();
                TreeNode node = (TreeNode) data[0];
                int depth = (int) data[1];

                if (depth > row) {
                    flushRow(children, sb);
                    row = depth;
                    children.clear();
                }
                children.add(node.left);
                children.add(node.right);

                if (node.left != null) {
                    queue.add(new Object[] {node.left, depth + 1});
                }
                if (node.right != null) {
                    queue.add(new Object[] {node.right, depth + 1});
                }
            }
            if (!children.isEmpty()) {
                flushRow(children, sb);
            }
            return sb.toString();
        }

        private void flushRow(ArrayList<TreeNode> children, StringBuilder sb) {
            for (TreeNode node : children) {
                sb.append(" ");
                sb.append(node != null ? String.valueOf(node.val) : '.');
            }
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] parts = data.split(" ");
            TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
            List<Integer> values = new ArrayList<>();
            List<TreeNode> parents = new ArrayList<>();
            parents.add(root);
            for (int index = 1; index < parts.length; index++) {
                String part = parts[index];
                values.add(part.equals(".") ? null : Integer.parseInt(part));
                if (values.size() == parents.size()  * 2) {
                    parents = addNodes(parents, values);
                    values.clear();
                }
            }
            return root;
        }

        private List<TreeNode> addNodes(List<TreeNode> parents, List<Integer> values) {
            List<TreeNode> children = new ArrayList<>();
            for (int i = 0; i < parents.size(); i++) {
                TreeNode parent = parents.get(i);
                Integer left = values.get(i * 2);
                if (left != null) {
                    TreeNode child = new TreeNode(left);
                    parent.left = child;
                    children.add(child);
                }
                Integer right = values.get(i * 2 + 1);
                if (right != null) {
                    TreeNode child = new TreeNode(right);
                    parent.right = child;
                    children.add(child);
                }
            }
            return children;
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
