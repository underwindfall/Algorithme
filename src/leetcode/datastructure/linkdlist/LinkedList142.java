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

    // time O(N)
    // espace O(1)
    class FastSlowIndex {
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }

            ListNode slow = head, fast = head.next;

            // cyclic -> fast == slow <==> （fast - slow） 路程 % cycle == 0 可以被整除
            // a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
            // fast = m + (n + 1)a + nc
            // slow = m + a
            // fast == slow
            // m + (n + 1)a +nc = 2(m+a) <==> m = (n - 1)a + nc
            // start 在走a步骤 就可以整圈

            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return null;
                }

                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode start = head;
            slow = slow.next;
            while (slow != start) {
                slow = slow.next;
                start = start.next;
            }
            return start;
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
