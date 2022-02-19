package swordoffer;

//https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
public class LCOF24 {
    // time O(n)
    // space O(1)
    class Recursive {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return next;
        }
    }

    //time O(n)
    //space O(1)
    class DummyNode {
        public ListNode reverseList(ListNode head) {
            ListNode dummy = new ListNode(-1);
            while (head.next != null) {
                ListNode next = head.next;
                head.next = dummy.next;
                dummy.next = head;
                head = next;
            }
            return dummy.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
