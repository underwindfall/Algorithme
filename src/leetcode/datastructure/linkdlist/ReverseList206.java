package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/reverse-linked-list/
public class ReverseList206 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // time O(N)
    //espace O(N)
    class Recursive {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            ListNode newHead = reverseList(next);
            next.next = head;
            head.next = null;
            return newHead;
        }
    }

    //头插法
    //time O(N)
    //espace O(1)
    class DummyNode {
        public ListNode reverseList(ListNode head) {
            ListNode dummy = new ListNode(-1);
            while (head != null) {
                ListNode next = head.next;
                head.next = dummy.next;
                dummy.next = head;
                head = next;
            }
            return dummy.next;
        }
    }
}
