package code.leetcode.easy;

import org.junit.jupiter.api.Test;

import code.leetcode.ListNode;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return true;
            }
            p1 = p1.next;
            p2 = p2.next == null ? null : p2.next.next;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        // Two pointers initialized to the start of the list
        ListNode fast = head;
        ListNode slow = head;

        // Loop until the fast pointer reaches the end of the list
        while (fast != null && fast.next != null) {
            // Move the slow pointer by one step
            slow = slow.next;
            // Move the fast pointer by two steps
            fast = fast.next.next;
            // If they meet, a cycle is detected
            if (slow == fast) {
                // Initialize another pointer to the start of the list
                ListNode start = head;
                // Move both pointers at the same pace
                while (start != slow) {
                    // Move each pointer by one step
                    start = start.next;
                    slow = slow.next;
                }
                // When they meet again, it's the start of the cycle
                return start;
            }
        }
        // If we reach here, no cycle was detected
        return null;
    }

    @Test
    void test() {

    }
}
