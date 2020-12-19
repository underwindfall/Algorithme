package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/middle-of-the-linked-list/
public class MiddleNode876 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(1)
    // fast slow index
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
