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

    // 哨兵节点
    // 遍历两次 第一次获取到listnode 长度
    // 第二次 剔除length - n节点
    class DummyNode {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int count = 0;
            ListNode dummy = head;
            while (dummy != null) {
                count++;
                dummy = dummy.next;
            }

            for (int i = 0; i <= count - n - 1; i++) {
                dummy = dummy.next;
            }
            dummy.next = dummy.next.next;
            return dummy;
        }
    }

    class Recursive {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int pos = getListLenghtAndRemoveNthFromEnd(head, n);
            if (pos == n) {
                return head.next;
            }
            return head;
        }

        private int getListLenghtAndRemoveNthFromEnd(ListNode node, int n) {
            if (node == null) {
                return 0;
            }
            int pos = getListLenghtAndRemoveNthFromEnd(node.next, n) + 1;
            if (pos == n + 1) {
                node.next = node.next.next;
            }
            return pos;
        }

        // 题目有个变形就是求linkedList的长度
        int getListLength(ListNode node) {
            if (node == null) {
                return 0;
            }
            int pos = getListLength(node.next) + 1;
            return pos;
        }
    }
}
