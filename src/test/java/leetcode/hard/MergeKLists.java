package leetcode.hard;

import static leetcode.ListNode.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import leetcode.ListNode;

class MergeKLists {
    public ListNode mergeKListsNoRecursion(ListNode[] nodes) {
        if (nodes.length == 0) {
            return null;
        }

        ListNode first = null;
        ListNode last = null;
        while (true) {
            int min = Integer.MAX_VALUE;
            for (ListNode node : nodes) {
                if (node != null) {
                    min = Math.min(min, node.val);
                }
            }
            if (min == Integer.MAX_VALUE) {
                break;
            }

            for (int i = 0; i < nodes.length; i++) {
                ListNode node = nodes[i];
                if (node != null) {
                    if (node.val == min) {
                        if (first == null) {
                            first = node;
                            last = node;
                        } else {
                            last.next = node;
                            last = node;
                        }
                        nodes[i] = node.next;
                    }
                }
            }
        }
        return first;
    }

    public ListNode mergeKLists(ListNode[] nodes) {
        if (nodes.length == 0) {
            return null;
        } else if (nodes.length == 1) {
            return nodes[0];
        } else if (nodes.length == 2) {
            return mergeTwoLists(nodes[0], nodes[1]);
        } else {
            int mid = nodes.length / 2;
            ListNode[] nodesLeft = Arrays.copyOfRange(nodes, 0, mid);
            ListNode[] nodesRight = Arrays.copyOfRange(nodes, mid, nodes.length);
            ListNode left = mergeKLists(nodesLeft);
            ListNode right = mergeKLists(nodesRight);
            return mergeTwoLists(left, right);
        }
    }

    public ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode first = null;
        ListNode last = null;
        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val <= right.val) {
                    // left
                    if (first == null) {
                        first = left;
                        last = left;
                    } else {
                        last.next = left;
                        last = left;
                    }
                    left = left.next;
                } else {
                    //right
                    if (first == null) {
                        first = right;
                        last = right;
                    } else {
                        last.next = right;
                        last = right;
                    }
                    right = right.next;
                }
            } else if (left != null) {
                //left
                if (first == null) {
                    first = left;
                    last = left;
                } else {
                    last.next = left;
                    last = left;
                }
                left = left.next;
            } else if (right != null) {
                //right
                if (first == null) {
                    first = right;
                    last = right;
                } else {
                    last.next = right;
                    last = right;
                }
                right = right.next;
            }
        }
        return first;
    }

    @Test
    void test2() {
        ListNode res = mergeKLists(new ListNode[] {fromString("1,4,5"), fromString("1,3,4")});
        assertEquals("[1,1,3,4,4,5]", res.print());
    }

    @Test
    void test3() {
        ListNode res = mergeKLists(new ListNode[] {fromString("1,4,5"), fromString("1,3,4"), fromString("2,6")});
        assertEquals("[1,1,2,3,4,4,5,6]", res.print());
    }

    @Test
    void test4() {
        ListNode res = mergeKLists(
                new ListNode[] {fromString("1,4,5"), fromString("1,3,4"), fromString("2,6"), fromString("9")});
        assertEquals("[1,1,2,3,4,4,5,6,9]", res.print());
    }
}
