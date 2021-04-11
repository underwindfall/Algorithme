package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/remove-linked-list-elements/
public class RemoveEleInList203 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy, curr = head;

        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
