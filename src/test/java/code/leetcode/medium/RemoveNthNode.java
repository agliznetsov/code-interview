package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import code.utils.ListNode;

class RemoveNthNode {
    public ListNode removeNthFromEnd(final ListNode head, int n) {
        ListNode res = head;
        ListNode mid = head;
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
            if (n > 0) {
                n--;
            } else {
                mid = mid.next;
            }
        }
        if (n == 1) {
            return head.next;
        } else {
            if (mid.next != null) {
                mid.next = mid.next.next;
                return res;
            } else {
                return null;
            }
        }
    }

    @Test
    void test1() {
        ListNode head = ListNode.fromString("1,2,3,4,5");
        head = removeNthFromEnd(head, 2);
        assertEquals("[1,2,3,5]", head.print());
    }

    @Test
    void test2() {
        ListNode head = ListNode.fromString("1,2,3");
        head = removeNthFromEnd(head, 3);
        assertEquals("[2,3]", head.print());
    }
}
