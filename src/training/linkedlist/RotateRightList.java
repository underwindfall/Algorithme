package training.linkedlist;


// https://leetcode-cn.com/problems/rotate-list
/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 
 * 输出: 4->5->1->2->3->NULL
 * 
 * 解释: 向右旋转 1 步: 5->1->2->3->4->NULL 向右旋转 2 步: 4->5->1->2->3->NULL
 * 
 * 
 * 
 * 示例 2:
 * 
 * 输入: 0->1->2->NULL, k = 4
 * 
 * 输出: 2->0->1->NULL
 * 
 * 
 * 解释: 向右旋转 1 步: 2->0->1->NULL 向右旋转 2 步: 1->2->0->NULL 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * 
 * 
 */
public class RotateRightList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 思路
    // 将整个list 变成一个环形list， 最后的节点指向起点
    // 然后根据测试得出结论：转动后的最后的节点是原来list的 第length - movement - 1
    // 通过循环可以得出这个新的尾节点，和头节点
    // 断开尾节点
    // time: O(N)
    // espace:O(1)
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        // 获取到开始最后的tail node 然后将它连城环
        ListNode oldTail = head;
        int length = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            length++;
        }
        oldTail.next = head;

        int movement = k % length;
        ListNode newTail = head;
        for (int i = 0; i < length - movement - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}