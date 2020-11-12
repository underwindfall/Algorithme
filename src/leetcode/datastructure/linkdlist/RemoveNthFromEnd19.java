package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthFromEnd19 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class FastSlowIndex {
        // 当fast 和 slow 之间相距N个距离
        // 这时候 fast移动至ListNode队尾 slow就是倒数N的前一个数字
        // 删除这个ListNode就好
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode fast = head;
            ListNode slow = head;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            if (fast == null) {
                return head.next;
            }

            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;
            return head;
        }
    }

    class Recursive {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int pos = getNthFromEnd(head, n);
            if (pos == n) {
                return head.next;
            }
            return head;
        }

        private int getNthFromEnd(ListNode node, int n) {
            if (node == null) {
                return 0;
            }
            int pos = getNthFromEnd(node, n) + 1;
            if (pos == n + 1) {
                node.next = node.next.next;
            }
            return pos;
        }
    }
}
