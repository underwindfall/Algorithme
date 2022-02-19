package swordoffer;

import java.util.Stack;

//https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
public class LCOF22 {

    /**
     * 顺序查找
     * 先走k步，慢指针然后再走
     * time O(n)
     * space O(1)
     */
    class DoubleIndex {
        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode former = head, latter = head;
            for (int i = 0; i < k; i++)
                former = former.next;
            while (former != null) {
                former = former.next;
                latter = latter.next;
            }
            return latter;
        }
    }

    // time O(n)
    // space O(1)
    class Recursive {
        public ListNode getKthFromEnd(ListNode head, int k) {
            return dfs(head, k).getKey();
        }

        Pair<ListNode, Integer> dfs(ListNode head, int k) {
            if (head.next == null) {
                return new Pair<>(head, 1);
            }
            Pair<ListNode, Integer> next = dfs(head.next, k);
            if (next.getValue() == k) {
                return next;
            }
            return new Pair<>(head, next.getValue() + 1);
        }

        class Pair<A, B> {
            A key;
            B value;

            Pair(A key, B value) {
                this.key = key;
                this.value = value;
            }

            A getKey() {
                return key;
            }

            B getValue() {
                return value;
            }
        }
    }

    // time O(n)
    // space O(n)
    class StackSolution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            Stack<ListNode> stack = new Stack<>();
            ListNode tmp = head;
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.next;
            }
            while (!stack.isEmpty() && k > 1) {
                stack.pop();
                k--;
            }
            return stack.peek();
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
