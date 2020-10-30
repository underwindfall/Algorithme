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

    // 快慢指针
    /**
     * 找到前半部分链表的尾节点。
     * 
     * 反转后半部分链表。
     * 
     * 判断是否回文。
     * 
     * 恢复链表。
     * 
     * 返回结果
     */

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 找到前半部分链表的尾节点。
        ListNode fast = head.next;
        ListNode firstHalfEnd, secondHalfStart = null;
        ListNode slow = head;// firstHalfEnd
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        firstHalfEnd = slow;
        slow = slow.next;

        ListNode prev = null;// secondHalfStart
        ListNode curr = firstHalfEnd.next;
        // 反转后半部分链表。
        while (curr != null) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }

        secondHalfStart = prev;

        // 判断回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原结果
        ListNode anotherPrev = null;
        while (secondHalfStart != null) {
            ListNode nextTmp = secondHalfStart.next;
            secondHalfStart.next = anotherPrev;
            anotherPrev = secondHalfStart;
            secondHalfStart = nextTmp;
        }
        firstHalfEnd.next = anotherPrev;
        return result;
    }

    // 嵌套
    // 因为嵌套的特性实际上又回是代码分别从头和尾进行比较
    // time:O(N)
    // espace:O(N)
    // 其中 n
    // 指的是链表的大小。我们要理解计算机如何运行递归函数，在一个函数中调用一个函数时，
    // 计算机需要在进入被调用函数之前跟踪它在当前函数中的位置（以及任何局部变量的值），
    // 通过运行时存放在堆栈中来实现（堆栈帧）。在堆栈中存放好了数据后就可以进入被调用的函数。
    // 在完成被调用函数之后，他会弹出堆栈顶部元素，以恢复在进行函数调用之前所在的函数。在进行回文检查之前，递归函数将在堆栈中创建
    // nn 个堆栈帧，计算机会逐个弹出进行处理。所以在使用递归时空间复杂度要考虑堆栈的使用情况。

    private ListNode frontPointer;

    public boolean isPalindrome1(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(frontPointer);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            // 一直循环到最后一个节点
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
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
