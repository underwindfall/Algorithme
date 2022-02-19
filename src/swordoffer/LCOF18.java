package swordoffer;

// https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
public class LCOF18 {
    // 哨兵节点
    // time O(n)
    // space O(1)
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next = curr.next;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = next;
            }

            prev = curr;
            curr = next;
            if (curr != null) {
                next = curr.next;
            }
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
