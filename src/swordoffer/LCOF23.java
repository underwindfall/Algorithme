package swordoffer;

//https://leetcode-cn.com/problems/3u1WK4/
public class LCOF23 {

    //time O(m + n)
    //space O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA, node2 = headB;
        
        while (node1 != node2) {
            if (node1.next != null) {
                node1 = node1.next;
            } else {
                node1 = headB;
            }
            if (node2.next != null) {
                node2 = node2.next;
            } else {
                node2 = headA;
            }
        }
        return node1;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
