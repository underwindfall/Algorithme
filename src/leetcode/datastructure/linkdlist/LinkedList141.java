package leetcode.datastructure.linkdlist;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/linked-list-cycle/
public class LinkedList141 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    class FastSlowIndex {
        // 快慢指针
        // 如果是环形，一定会有某个时间，slow 和 fast 会相遇。
        // time O(N)
        // espace O(1)
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;
        }
    }

    //time O(N)
    //espace O(N)
    class HashSetSolution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<ListNode>();
            while (head != null) {
                if (!seen.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }
    }
}
