package training.linkedlist;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/palinPadrome-linked-list/
/**
 * 请判断一个链表是否为回文链表。
 * 
 * 示例 1:
 * 
 * 输入: 1->2 输出: false 示例 2:
 * 
 * 输入: 1->2->2->1 输出: true
 */
public class PalinpadromeLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 用空间换时间， 数组来转换LinkedList 然后
    // time O(N+N) = O(N)
    // espace O(N)
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) {
            vals.add(head.val);
            head = head.next;
        }
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (vals.get(front) != vals.get(back)) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
