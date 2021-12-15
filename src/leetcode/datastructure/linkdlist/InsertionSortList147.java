package leetcode.datastructure.linkdlist;

// https://leetcode.com/problems/insertion-sort-list/
public class InsertionSortList147 {
    // time O(n^2)
    // space O(1)
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = null;
        ListNode temp = null;
        while (curr.next != null) {
            if (curr.val <= curr.next.val) {
                curr = curr.next;
            } else {
                temp = curr.next;
                curr.next = curr.next.next;
                prev = dummy;
                while (prev.next.val <= temp.val) {
                    prev = prev.next;
                }
                temp.next = prev.next;
                prev.next = temp;
            }
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
