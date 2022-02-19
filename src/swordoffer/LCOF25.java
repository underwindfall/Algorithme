package swordoffer;

import java.util.Stack;

//https://leetcode-cn.com/problems/lMSNwu/
public class LCOF25 {
    // time O(n+m)
    // spaceO(m+n)
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

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
