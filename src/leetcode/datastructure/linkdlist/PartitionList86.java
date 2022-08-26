package leetcode.datastructure.linkdlist;

// https://leetcode.cn/problems/partition-list/
public class PartitionList86 {
    // time O(n)
    // space O(n)
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode();
        ListNode dummy2 = new ListNode();
        ListNode l1 = dummy1, l2 = dummy2;
        while (head != null) {
            if (head.val < x) {
                l1.next = new ListNode(head.val);
                l1 = l1.next;
            } else {
                l2.next = new ListNode(head.val);
                l2 = l2.next;
            }
            head = head.next;
        }
        l1.next = dummy2.next;
        return dummy1.next;
    }

    // https://leetcode.cn/problems/partition-list/solution/java-di-gui-100-by-programmery-jvai/
    class Recursive {
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) {
                return head;
            }
            head.next = partition(head.next, x);
            if (head.val >= x && head.next.val < x) {
                ListNode newHead = head.next;
                ListNode ptr = head;
                while (ptr.next != null && ptr.next.val < x) {
                    ptr = ptr.next;
                }
                head.next = ptr.next;
                ptr.next = head;
                return newHead;
            } else {
                return head;
            }
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
