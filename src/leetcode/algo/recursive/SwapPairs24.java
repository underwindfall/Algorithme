package leetcode.algo.recursive;

public class SwapPairs24 {
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

    /**
     * // 1.针对head 和 head.next 进行交换
     * 
     * // 2.递归函数是swapPairs (head.next.next)
     * 
     * // 3.递归函数是swapPairs返回得解 是head的下一个node
     * 
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    // 哨兵解法
    class Iterative {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode tmp = dummy;
            while (tmp.next != null && tmp.next.next != null) {
                ListNode node1 = tmp.next;
                ListNode node2 = tmp.next.next;

                tmp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                tmp = node1;
            }
            return dummy.next;
        }
    }
}
