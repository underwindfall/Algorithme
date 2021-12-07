package leetcode.algo.dfs;

// https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class GetDecimalValue1290 {
    // time O(n)
    // space O(1)
    class Recursive {
        public int getDecimalValue(ListNode head) {
            return dfs(head)[1];
        }

        // level, sum
        int[] dfs(ListNode head) {

            if (head.next == null) {
                return new int[] { 0, head.val };
            }

            int[] next = dfs(head.next);

            int level = next[0] + 1;
            int sum = next[1] + head.val * (int) Math.pow(2, level);

            return new int[] { level, sum };
        }
    }

    // time O(n)
    // space O(1)
    class Iterative {
        public int getDecimalValue(ListNode head) {
            int sum = head.val;
            while (head.next != null) {
                sum = sum * 2 + head.next.val;
                head = head.next;
            }
            return sum;
        }
    }

    class ListNode {
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
}
