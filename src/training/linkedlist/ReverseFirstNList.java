package training.linkedlist;

// 反转链表前 N 个节点
// https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.2-shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
public class ReverseFirstNList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    ListNode successor = null;

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        // head 的 下一个 的下一个是successor
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    public static void main(String[] args) {
        ReverseFirstNList rotateList61 = new ReverseFirstNList();
        ReverseFirstNList.ListNode head = rotateList61.new ListNode(1);
        ReverseFirstNList.ListNode node2 = rotateList61.new ListNode(2);
        ReverseFirstNList.ListNode node3 = rotateList61.new ListNode(3);
        ReverseFirstNList.ListNode node4 = rotateList61.new ListNode(4);
        ReverseFirstNList.ListNode node5 = rotateList61.new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = rotateList61.reverseN(head, 4);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
