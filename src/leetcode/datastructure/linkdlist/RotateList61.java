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

    // time :O(N)
    // espace O(1)
    class RingList {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            int length = 1;
            ListNode curr = head;
            while (curr.next != null) {
                length++;
                curr = curr.next;
            }
            // make it as cirle
            curr.next = head;

            curr = head;

            // head 头指针走到的位置index
            int cutPoint = k % length;
            // 还有几步到达尾节点
            int move = length - cutPoint - 1;

            // curr到达尾节点
            for (int i = 0; i < move; i++) {
                curr = curr.next;
            }

            // 把尾节点断开 不再是一个circle
            ListNode tmp = curr;
            // 头节点
            curr = curr.next;
            tmp.next = null;
            return curr;
        }
    }

    class FastSlowIndex {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0)
                return head;
            // 计算有效的 k 值：对于与链表长度成整数倍的「旋转」都是没有意义的（旋转前后链表不变）
            int tot = 0;
            ListNode tmp = head;
            while (tmp != null && ++tot > 0)
                tmp = tmp.next;
            k %= tot;
            if (k == 0)
                return head;

            // 使用「快慢指针」找到倒数第 k 个节点（新头结点）：slow 会停在「新头结点」的「前一位」，也就是「新尾结点」
            ListNode slow = head, fast = head;
            while (k-- > 0)
                fast = fast.next;
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            // 保存新头结点，并将新尾结点的 next 指针置空
            ListNode nHead = slow.next;
            slow.next = null;
            // 将新链表的前半部分（原链表的后半部分）与原链表的头结点链接上
            fast.next = head;
            return nHead;
        }
    }

    // 闭合思路
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
