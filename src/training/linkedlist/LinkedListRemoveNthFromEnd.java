package training.linkedlist;

import java.util.Stack;

//https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * 示例：
 * 
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5. 说明：
 * 
 * 给定的 n 保证是有效的。
 * 
 * 进阶：
 * 
 * 你能尝试使用一趟扫描实现吗
 */
public class LinkedListRemoveNthFromEnd {
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

    // 循环一次 双指针解决 first 节点比second 节点快N个
    //time : O(N)
    //espace :O(1)
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next; 
        ListNode ans = dummy.next;
        return ans;
    }

    // 用栈解决
    // time O(N)
    // espace O(N)
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = dummy;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        ListNode node = stack.peek();
        node.next = node.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    // 就是死循环一遍
    // time O(N)
    // espace O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode curr = dummy;
        for (int i = 1; i < length - n + 1; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
