package training.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionTwoLinkedList {
    // 双指针法
    public MyLinkedList.ListNode getIntersectionNode2(MyLinkedList.ListNode headA, MyLinkedList.ListNode headB) {
        MyLinkedList.ListNode p = headA, q = headB;
        while (p != q) {
            p = (p == null) ? headB : p.next;
            q = (q == null) ? headA : q.next;
        }
        return p;
    }

    // hashset
    // time: O(M+N)
    // espace: O(M)
    public MyLinkedList.ListNode getIntersectionNode1(MyLinkedList.ListNode headA, MyLinkedList.ListNode headB) {
        Set<MyLinkedList.ListNode> set = new HashSet<MyLinkedList.ListNode>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB))
                return headB;
            else
                headB = headB.next;
        }
        return null;
    }

    // 暴力解法循环
    // time : O(M*N)
    // espace: O(1)
    public MyLinkedList.ListNode getIntersectionNode(MyLinkedList.ListNode headA, MyLinkedList.ListNode headB) {
        while (headA != null) {
            MyLinkedList.ListNode result = getNextNode(headA, headB);
            if (result != null) {
                return result;
            }
            headA = headA.next;
        }
        return null;
    }

    private MyLinkedList.ListNode getNextNode(MyLinkedList.ListNode headA, MyLinkedList.ListNode headB) {
        while (headB != null) {
            if (headA != headB) {
                headB = headB.next;
            } else {
                return headB;
            }
        }
        return null;
    }

    public static void main(String[] args) {
    }

}
