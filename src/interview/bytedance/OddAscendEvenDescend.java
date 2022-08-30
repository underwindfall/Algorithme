package interview.bytedance;

// https://mp.weixin.qq.com/s/0WVa2wIAeG0nYnVndZiEXQ
// 给定一个奇数位升序，偶数位降序的链表，将其重新排序。

// 输入: 1->8->3->6->5->4->7->2->NULL
// 输出: 1->2->3->4->5->6->7->8->NULL
public class OddAscendEvenDescend {
    // 本题是典型的链表类型的题目，题目涉及到的操作比较多，但是都不会很难。
    // 考虑到原始链表中存在两种类型的节点，即奇节点和偶节点，所以我们可以使用两个链表来分别存储以更好地解决问题。
    // 将链表拆分为奇偶链表后，由于原先的偶链表是降序的，我们需要先将其翻转，使之变成升序的链表。
    // 最后，两个链表都是有序的，那么我们很容易就可以对这两个链表进行合并以达到排序链表的作用。

    public static ListNode sortList(ListNode head) {
        // 首先拆分奇偶链表

        ListNode dumyOdd = new ListNode(-1);
        ListNode odd = head;
        dumyOdd = odd;
        ListNode dumyEven = new ListNode(-1);
        ListNode even = head.next;
        dumyEven = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = null;// 将尾节点置为null
        even.next = null;// 将尾节点置为null

        even = reverseList(dumyEven);
        odd = dumyOdd;

        return mergeList(odd, even);
    }

    static ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    static ListNode mergeList(ListNode nodeA, ListNode nodeB) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                curr.next = nodeA;
                nodeA = nodeA.next;
            } else {
                curr.next = nodeB;
                nodeB = nodeB.next;
            }
            curr = curr.next;
        }
        if (nodeA == null) {
            curr.next = nodeB;
        } else {
            curr.next = nodeA;
        }
        return dummy.next;
    }

    public static void main(String args[]) {
        int[] input = { 1, 8, 3, 6, 5, 4, 7, 2 };
        ListNode dumyOri = new ListNode(-1);
        ListNode ori = dumyOri;
        for (int i = 0; i < input.length; i++) {
            ori.next = new ListNode(input[i]);
            ori = ori.next;
        }
        ori = null;

        ListNode res = sortList(dumyOri.next);
        while (res != null) {
            System.out.print(res.val + ", ");
            res = res.next;
        }
    }

    static class ListNode {
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
