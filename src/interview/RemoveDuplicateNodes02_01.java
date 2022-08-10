package interview;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/remove-duplicate-node-lcci/
public class RemoveDuplicateNodes02_01 {
    // time O(n)
    // space O(n)
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode prev = head;
        while (prev.next != null) {
            ListNode cur = prev.next;
            if (set.add(cur.val)) {
                prev = prev.next;
            } else {
                prev.next = prev.next.next;
            }
        }
        prev.next = null;
        return head;
    }

    // time O(n^2)
    // space O(1)
    class OptimisationEspace {
        public ListNode removeDuplicateNodes(ListNode head) {
            ListNode ob = head;
            while (ob != null) {
                ListNode oc = ob;
                while (oc.next != null) {
                    if (oc.next.val == ob.val) {
                        oc.next = oc.next.next;
                    } else {
                        oc = oc.next;
                    }
                }
                ob = ob.next;
            }
            return head;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
