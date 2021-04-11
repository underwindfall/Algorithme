package leetcode.datastructure.linkdlist;

import java.util.Stack;

// https://leetcode-cn.com/problems/palindrome-linked-list/description/
public class PalindromeList234 {
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

    class Recursive {
        private ListNode tmp;

        public boolean isPalindrome(ListNode head) {
            tmp = head;
            return recursiveCheck(head);
        }

        private boolean recursiveCheck(ListNode node) {
            if (node == null) {
                return true;
            }
            boolean res = recursiveCheck(node.next) && (node.val == tmp.val);
            tmp = tmp.next;
            return res;
        }
    }

    class FastSlowIndex {
        public boolean isPalindrome(ListNode head) {
            ListNode fast = head, slow = head;
            // 通过快慢指针找到中点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            // 如果fast不为空，说明链表的长度是奇数个
            if (fast != null) {
                slow = slow.next;
            }
            // 反转后半部分链表
            slow = reverse(slow);

            fast = head;
            while (slow != null) {
                // 然后比较，判断节点值是否相等
                if (fast.val != slow.val)
                    return false;
                fast = fast.next;
                slow = slow.next;
            }
            return true;
        }

        // 反转链表
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

    }

    // time O(N)
    // espace O(N)
    class StackSolution {
        public boolean isPalindrome(ListNode head) {
            // reverse 一下 一个个比较
            Stack<ListNode> stack = new Stack<>();
            ListNode cur = head;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            while (!stack.isEmpty()) {
                if (head.val != stack.pop().val) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }
    }
}
