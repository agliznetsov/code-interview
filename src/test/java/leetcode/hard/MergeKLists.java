package leetcode.hard;

import static leetcode.ListNode.fromString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import leetcode.ListNode;

class MergeKLists {
    public ListNode mergeKLists(ListNode[] nodes) {
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

    @Test
    void test() {
        ListNode res = mergeKLists(new ListNode[] {fromString("1,4,5"), fromString("1,3,4"), fromString("2,6")});
        assertEquals("[1,1,2,3,4,4,5,6]", res.print());
    }
}
