package swordoffer;

//https://leetcode-cn.com/problems/c32eOV/
public class LCOF22_II {

    // time O(n)
    // space O(1)
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null) {
            return null;
        }
        ListNode start = head;
        while (start != slow) {
            start = start.next;
            slow = slow.next;
        }
        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
