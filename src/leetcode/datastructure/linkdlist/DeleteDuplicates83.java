package leetcode.datastructure.linkdlist;

import java.util.HashSet;
import java.util.Set;

//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
public class DeleteDuplicates83 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class SetSolution {
        public ListNode deleteDuplicates(ListNode head) {
            Set<Integer> set = new HashSet<>();

            ListNode prev = null, curr = head;
            while (curr != null) {
                if (set.contains(curr.val)) {
                    prev.next = curr.next;
                } else {
                    set.add(curr.val);
                    prev = curr;
                }
                curr = curr.next;
            }
            return head;
        }
    }

    // time O(N)
    // espace O(N)
    class Recursive {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            head.next = deleteDuplicates(head.next);
            return head.val == head.next.val ? head.next : head;
        }
    }

    class Iterative {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode curr = head;
            while (curr != null && curr.next != null) {
                if (curr.val == curr.next.val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
            return head;
        }
    }

    class FastSlowIndex {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode fast = head, slow = head;
            while (fast != null) {
                if (slow.val != fast.val) {
                    slow.next = fast;
                    slow = fast;
                }
                fast = fast.next;
            }
            slow.next = null;
            return head;
        }
    }

}
