package training.linkedlist;

//https://leetcode-cn.com/problems/remove-linked-list-elements
/**
 * 删除链表中等于给定值 val 的所有节点。
 * 
 * 示例:
 * 
 * 输入: 1->2->6->3->4->5->6, val = 6 输出: 1->2->3->4->5
 */
public class RemoveLinkedListElement {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // time:O(N)
    // espace:O(1)
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode curr = head;
        ListNode result = null;
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val == val) {
                prev.next = next;
                curr = next;
            } else {
                if (result == null) {
                    result = prev.next;
                }
                prev = curr;
                curr = next;
            }
        }
        return result;
    }

    //官方答案 
    //哨兵节点 没什么实际的意义 这里就是伪头数据 两者的思路一样
    //time O(N)
    //espace O(1)
    public ListNode removeElements1(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val)
                prev.next = curr.next;
            else
                prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }

}
