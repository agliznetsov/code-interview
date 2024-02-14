package leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode fromString(String str) {
        String[] parts = str.split(",");
        ListNode first = null;
        ListNode last = null;
        for (String s : parts) {
            ListNode next = new ListNode(Integer.parseInt(s));
            if (first == null) {
                first = next;
                last = next;
            } else {
                last.next = next;
                last = next;
            }
        }
        return first;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        ListNode node = this;
        while (node != null) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(node.val);
            node = node.next;
        }
        return "[" + sb + "]";
    }

    @Test
    void test() {
        assertEquals("1,2,3", ListNode.fromString("1,2,3").print());
    }
}
