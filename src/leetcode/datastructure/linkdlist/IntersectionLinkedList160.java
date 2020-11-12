package leetcode.datastructure.linkdlist;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
public class IntersectionLinkedList160 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //time O(M+N)
    //espace O(M)
    class HashSetSolution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> set = new HashSet<>();
            while (headA != null) {
                set.add(headA);
                headA = headA.next;
            }
            while (headB != null) {
                if (set.contains(headB)) {
                    return headB;
                } else {
                    headB = headB.next;
                }
            }
            return null;
        }
    }

    //思路
    //双指针
    //讲两个List拼接成一个List
    //A走完走B B走完走A
    //相遇的地方就停下来就是开始交叉的点
    //time O(M+N)
    //espace O(1)
    class DoubleIndex {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode ha = headA, hb = headB;
            while (ha != hb) {
                ha = ha != null ? ha.next : headB;
                hb = hb != null ? hb.next : headA;
            }
            return ha;
        }
    }
}
