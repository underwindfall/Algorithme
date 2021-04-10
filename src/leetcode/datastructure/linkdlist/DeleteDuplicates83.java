package leetcode.datastructure.linkdlist;

//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
public class DeleteDuplicates83 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(N)
    class Recursive {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            head.next = deleteDuplicates(head.next);
            return head.val == head.next.val ? head.next : head;
        }
    }

    class Iterative {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.val == curr.next.val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            return head;
        }
    }

    class FastSlowIndex {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head, slow = head;
            while (fast != null) {
                if (slow.val != fast.val) {
                    slow.next = fast;
                    slow = fast;
                }
                fast = fast.next;
            }
            slow.next = null;
            return head;
        }
    }

}
