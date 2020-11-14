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

    //time O(Max(M,N))
    //espace O(M+N)
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

}
