package training.linkedlist;

import java.util.HashMap;

//https://leetcode-cn.com/problems/copy-list-with-random-pointer
/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 
 * 要求返回这个链表的 深拷贝。 
 * 
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * 
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 
 * 
 * 
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 
 */

public class CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        Node ptr = head;

        // A->B->C
        // A->A'->B->B'->C->C'
        while (ptr != null) {
            Node nodeClone = new Node(ptr.val);
            // 复制节点下一个连接
            nodeClone.next = ptr.next;
            // 连接当前节点
            ptr.next = nodeClone;
            ptr = nodeClone.next;
        }

        ptr = head;

        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned
        // list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'

        Node ptrOldList = head;// A->B->C
        Node ptrNewList = head.next;// A'->B'->C'
        Node headOld = head.next;
        while (ptrOldList != null) {
            ptrOldList = ptrOldList.next.next;
            ptrNewList = (ptrNewList.next != null) ? ptrNewList.next.next : null;
            ptrOldList = ptrOldList.next;
            ptrNewList = ptrNewList.next;
        }

        return headOld;
    }

    /// 使用hashmap 存下每个节点 当存在的时候就返回这个值，不存在的时候
    // 意味着循环到了相应的尾节点
    // time: O(N)
    // espace: O(N)
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        Node node = new Node(head.val);

        this.visitedHash.put(head, node);

        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}
