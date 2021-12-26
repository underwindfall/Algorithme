package leetcode.datastructure.linkdlist;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/reorder-list/
public class ReorderList143 {

    // time O(n)
    // space O(n)
    class Normal {
        public void reorderList(ListNode head) {
            List<ListNode> nodes = new ArrayList<>();
            ListNode dummy = head;
            while (dummy != null) {
                nodes.add(dummy);
                dummy = dummy.next;
            }
            int i = 0, j = nodes.size() - 1;
            while (i < j) {
                ListNode next = nodes.get(i).next;
                nodes.get(i).next = nodes.get(j);
                nodes.get(j).next = next;
                i++;
                j--;
            }
            nodes.get(i).next = null;
        }
    }

    //time O(n)
    //space O(1)
    class HeadTail {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            // 找中点，链表分成两个
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            // 第二个链表倒置
            ListNode newHead = slow.next;
            slow.next = null;
            newHead = reverseList(newHead);
            // 链表节点依次连接
            while (newHead != null) {
                ListNode temp = newHead.next;
                newHead.next = head.next;
                head.next = newHead;

                head = newHead.next;
                newHead = temp;
            }
        }

        ListNode reverseList(ListNode head) {
            if (head.next == null) {
                return head;
            }

            ListNode temp = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return temp;
        }
    }

    class Recursive {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            int len = 0;
            ListNode h = head;
            while (h != null) {
                len++;
                h = h.next;
            }
            dfs(head, len);
        }

        ListNode dfs(ListNode head, int len) {
            if (len == 1) {
                ListNode outTail = head.next;
                head.next = null;
                return outTail;
            }
            if (len == 2) {
                ListNode outTail = head.next;
                head.next.next = null;
                return outTail;
            }
            // 得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
            ListNode tail = dfs(head.next, len - 2);
            ListNode subHead = head.next;
            head.next = tail;
            ListNode outTail = tail.next;
            tail.next = subHead;
            return outTail;
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
