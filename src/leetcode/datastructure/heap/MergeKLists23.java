package leetcode.datastructure.heap;

import java.util.PriorityQueue;

// https://leetcode.cn/problems/merge-k-sorted-lists/
public class MergeKLists23 {
    // time O(N *LogK)
    class Heap {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }

            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            while (!pq.isEmpty()) {
                ListNode minNode = pq.poll();
                tail.next = minNode;
                tail = minNode;
                if (minNode.next != null) {
                    pq.offer(minNode.next);
                }
            }
            return dummy.next;
        }
    }

    // time O(NlogK)
    class MergeList {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            return merge(lists, 0, lists.length - 1);
        }

        ListNode merge(ListNode[] lists, int lo, int hi) {
            if (lo == hi) {
                return lists[lo];
            }
            int mid = (hi - lo) / 2 + lo;
            ListNode l1 = merge(lists, lo, mid);
            ListNode l2 = merge(lists, mid + 1, hi);
            return merge2Lists(l1, l2);
        }

        ListNode merge2Lists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    l2 = l2.next;
                }
            }
            tail.next = l1 == null ? l2 : l1;
            return dummy.next;
        }
    }

    class ListNode {
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
