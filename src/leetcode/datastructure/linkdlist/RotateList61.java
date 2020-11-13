package leetcode.datastructure.linkdlist;

// https://leetcode-cn.com/problems/rotate-list/
public class RotateList61 {
    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }

        ListNode fast = head, slow = head;
        k = k % count;
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }

    public ListNode rotateLeft(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        ListNode fast = head;

        k = k % count;
        if (k == 0) {
            return head;
        }
        for (int i = 0; i < k - 1; i++) {
            fast = fast.next;
        }

        ListNode tail = head;
        for (int i = 0; i < count - 1; i++) {
            tail = tail.next;
        }

    
        tail.next = head;
        
        ListNode newHead = fast.next;
        ListNode newTail = fast;
        newTail.next = null;
        return newHead;

    }

    public static void main(String[] args) {
        RotateList61 rotateList61 = new RotateList61();
        RotateList61.ListNode head = rotateList61.new ListNode(1);
        RotateList61.ListNode node2 = rotateList61.new ListNode(2);
        RotateList61.ListNode node3 = rotateList61.new ListNode(3);
        RotateList61.ListNode node4 = rotateList61.new ListNode(4);
        RotateList61.ListNode node5 = rotateList61.new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // ListNode node = rotateList61.rotateRight(head, 2);
        ListNode node = rotateList61.rotateLeft(head, 4);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
