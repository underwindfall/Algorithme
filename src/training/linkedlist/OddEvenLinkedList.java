package training.linkedlist;

// https://leetcode-cn.com/problems/odd-even-linked-list/
/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL 输出: 1->3->5->2->4->NULL 示例 2:
 * 
 * 输入: 2->1->3->5->6->4->7->NULL 输出: 2->3->6->7->1->5->4->NULL
 */

public class OddEvenLinkedList {
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

    public ListNode oddEvenList(ListNode head) {
        // 特判：头结点为 null，返回null
        // head是奇链表的头
        if (head == null)
            return null;

        // odd是奇链表的当前节点，先初始化为head（初始化为奇链表头）
        ListNode odd = head;
        // even是偶链表的当前节点，初始化为第二个节点也就是head.next
        ListNode even = head.next;
        // evenHead是偶链表的头节点，初始化为链表第二个节点（初始化为奇链表头的下一个节点）
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            // 这里while退出判断条件还是画图一下才能理解（也就是官方题解的STEP2）
            odd.next = even.next; // 相当于odd.next = odd.next.next(跳过一个偶数节点)
            odd = odd.next; // odd向前前进一位
            even.next = odd.next; // 奇链表的下一个节点就是偶链表的节点
            even = even.next; // even向前前进一位
        }
        // while条件结束，把偶链表头指针拼接到奇链表的最后
        odd.next = evenHead;
        // 返回奇链表头就是返回整个奇偶排序后的链表
        return head;

    }
}
