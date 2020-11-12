package leetcode.datastructure.linkdlist;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/linked-list-cycle-ii/
public class LinkedList142 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // time
    // espace
    class FastSlowIndex {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while (true) {
                if (fast == null || fast.next == null) {
                    return null;
                }
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
    }

    // time O(N)
    // espace O(N)
    class HashSetSolution {
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> nodes = new HashSet<>();
            ListNode pos = head;
            while (pos != null) {
                if (nodes.contains(pos)) {
                    return pos;
                } else {
                    nodes.add(pos);
                }
                pos = pos.next;
            }
            return null;
        }
    }

}
