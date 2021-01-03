package leetcode.algo.recursive;

public class MergeList21 {
    public class ListNode {
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

    class Recursive {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            } else if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }

    class Iterative {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode preHead = new ListNode();
            ListNode prev = preHead;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    prev = l1;
                    l1 = l1.next;
                } else {
                    prev = l2;
                    l2 = l2.next;
                }

                prev = prev.next;
            }
            if (l1 != null) {
                prev.next = l1;
            } else {
                prev.next = l2;
            }
            return preHead.next;
        }
    }
}
