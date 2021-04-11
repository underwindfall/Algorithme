package leetcode.datastructure.linkdlist;

import java.util.Stack;

// https://leetcode-cn.com/problems/add-two-numbers-ii/description/
public class AddTwoNumbers445 {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    // time O(Max(M,N))
    // espace O(M+N)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        int carry = 0;

        ListNode temp = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            int sum = x + y + carry;
            int result = sum % 10;
            carry = sum / 10;
            ListNode node = new ListNode(result);
            node.next = temp;
            temp = node;
        }

        return temp;
    }

    public static void main(String[] args) {
        AddTwoNumbers445 addTwoNumbers445 = new AddTwoNumbers445();
        AddTwoNumbers445.ListNode node = addTwoNumbers445.addTwoNumbers(addTwoNumbers445.new ListNode(5),
                addTwoNumbers445.new ListNode(5));
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    // 题目的难点在于对于进位的把控，但是如果是按照数学逻辑运算的花，两数相加都是从低位到高位。 那我们翻转链表 然后计算， 最后结果翻转链表输出即可。
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // reverse list
            ListNode newL1 = reverse(l1);
            ListNode newL2 = reverse(l2);

            return reverse(recurisvie(newL1, newL2, 0));
        }

        ListNode recurisvie(ListNode l1, ListNode l2, int prefixPlus) {
            if (l1 == null && l2 == null && prefixPlus == 0) {
                return null;
            }
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + prefixPlus;
            int curr = sum % 10;
            prefixPlus = sum / 10;
            ListNode node = new ListNode(curr);
            node.next = recurisvie(l1 == null ? null : l1.next, l2 == null ? null : l2.next, prefixPlus);
            return node;
        }

        ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = reverse(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
}
