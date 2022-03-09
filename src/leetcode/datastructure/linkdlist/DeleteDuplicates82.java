package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
public class DeleteDuplicates82 {
    //time O(n)
    //space O(1)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode prev = dummy;
        ListNode curr = head;
        dummy.next = curr;
        while (curr != null) {
            if (curr.next != null && curr.next.val == curr.val) {
                while (curr.next != null && curr.next.val == curr.val) {
                    curr = curr.next;
                }
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
