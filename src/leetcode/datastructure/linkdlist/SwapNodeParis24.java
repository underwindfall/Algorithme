package leetcode.datastructure.linkdlist;

public class SwapNodeParis24 {
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

    // time O(N)
    // espace O(N)
    class Recursive {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode newHead = head.next;
            head.next = swapPairs(newHead.next);
            newHead.next = head;
            return newHead;
        }
    }

    //time O(N)
    //espace O(1)
    class Iterative {
        public ListNode swapPairs(ListNode head) {
            ListNode dummyNode = new ListNode();
            dummyNode.next = head;
            ListNode temp = dummyNode;
            while (temp.next != null && temp.next.next != null) {
                ListNode node1 = temp.next;
                ListNode node2 = temp.next.next;
                temp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                temp = node1;      
            }
            return dummyNode.next;
        }
    }
}
