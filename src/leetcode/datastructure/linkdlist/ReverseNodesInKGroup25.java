package leetcode.datastructure.linkdlist;

public class ReverseNodesInKGroup25 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
            this(0);
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    // 普通的方法通过设置每次补全的方式进行操作
    // [prev][1(start),2,3(end)][next] <->[prev] ->[3(end),2,1(start)]->[next]
    // 通过更细记录prev end 遍历操作即可
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode();
            dummy.next = head;

            ListNode prev = dummy;
            ListNode end = dummy;
            while (end.next != null) {
                for (int i = 0; i < k && end != null; i++) {
                    end = end.next;
                }

                // 不足k 不进行分组 直接返回
                if (end == null)
                    break;

                ListNode start = prev.next;
                ListNode next = end.next;

                end.next = null;
                prev.next = reverse(start);

                // 更新操作
                start.next = next;
                prev = start;
                end = prev;
            }
            return dummy.next;
        }

        // time O(N) -> N=b-a
        // espace O(1)
        private ListNode reverse(ListNode head) {
            ListNode prev = null, curr = head, next = head;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }

    /**
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/di-gui-java-by-reedfan-2/
     * 
     * [1(head), 2,3(tail)] <==> [ 2(newHead), 1] [3,4] <==> [ 2(newHead), 1] ->
     * [4,3]
     * 
     * 
     * newHead =reverseKGroup(head,2)
     * 
     * newnewHead = reverseKGroup(newHead,2)
     * 
     * 然后在当前newnewnewHead 为null 的时候返回
     */
    class RecursiveSolution {

        // time O(K^2)
        // espace O(N/K)
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                // 当不满足k个group的时候返回
                if (tail == null)
                    return head;
                tail = tail.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverse(head, tail);

            head.next = reverseKGroup(tail, k);
            return newHead;
        }

        /*
         * 左闭又开区间
         */
        ListNode reverse(ListNode head, ListNode tail) {
            ListNode prev = null, next = null;
            while (head != tail) {
                next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
