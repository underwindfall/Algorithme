package leetcode.datastructure.linkdlist;

public class ReverseNodesInKGroup25 {

    public class ListNode {
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

    /**
     * 解题方式 reverseKGroup 的 方法 简单来说可以考虑成
     * 
     * newHead =reverseKGroup(head,2)
     * 
     * newnewHead = reverseKGroup(newHead,2)
     * 
     * 然后在当前newnewnewHead 为null 的时候返回
     */
    static class RecursiveSolution {

        // time O(K^2)
        // espace O(N/K)
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            ListNode a, b;
            a = b = head;
            for (int i = 0; i < k; i++) {
                // 不足 k 个，不需要反转，base case
                if (b == null)
                    return head;
                b = b.next;
            }
            ListNode newHead = reverse(a, b);
            a.next = reverseKGroup(b, k);
            return newHead;
        }

        // 翻转[a,b)区间的node
        // time O(N) -> N=b-a
        // espace O(1)
        private ListNode reverse(ListNode a, ListNode b) {
            ListNode pre = null, cur = a, next = a;
            while (cur != b) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }

    public static ListNode reverseIterative(ListNode head) {
        ListNode pre = null, cur = head, next = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseRecursive(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursive(head.next);
        head.next = newHead;
        return newHead;
    }

}
