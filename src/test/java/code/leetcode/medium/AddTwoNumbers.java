package code.leetcode.medium;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import code.utils.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger a = toNumber(l1);
        BigInteger b = toNumber(l2);
        BigInteger c = a.add(b);
        return toList(c.toString());
    }

    private ListNode toList(String str) {
        ListNode next = null;
        for(char ch : str.toCharArray()) {
            ListNode node = new ListNode(Integer.valueOf(String.valueOf(ch)), next);
            next = node;
        }
        return next;
    }

    private BigInteger toNumber(ListNode node) {
        BigInteger res = BigInteger.ZERO;
        BigInteger num = BigInteger.ONE;
        while (node != null) {
            res = res.add(num.multiply(BigInteger.valueOf(node.val)));
            num = num.multiply(BigInteger.valueOf(10));
            node = node.next;
        }
        return res;
    }


    @Test
    void test() {

    }
}
