package leetcode.algo.recursive;

public class ReversList206 {
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

    //递归的思路是滞后发育 每次都只管N+1后面节点的生活不管之前的

    class Recursive {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }

    class Iteractive {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    }

    // 哨兵节点
    //dummyNext 永远指的是前一个节点
    class DummyNode {
        public ListNode reverseList(ListNode head) {
            ListNode dummy = new ListNode();
            while (head != null) {
                ListNode nextTemp = head.next;
                head.next = dummy.next;
                dummy.next = head;
                head = nextTemp;
            }
            return dummy.next;
        }
    }
}