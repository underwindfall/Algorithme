package swordoffer;

//https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
public class LCOF25_II {
    // time O(n+m)
    // spaceO(m+n)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode curr = new ListNode(-1);
        dummy.next = curr;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // curr = l1;
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummy.next.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
