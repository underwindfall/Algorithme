package leetcode.datastructure.linkdlist;

public class FlattenList430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    class DFS {

        //思路就是把这个链表的next当作右子树 child当作左子树 做前序遍历
        //time O(N)
        //espace O(N)
        public Node flatten(Node head) {
            if (head == null)
                return head;
            // pseudo head to ensure the `prev` pointer is never none
            Node pseudoHead = new Node(0, null, head, null);

            flattenDFS(pseudoHead, head);

            // detach the pseudo head from the real head
            pseudoHead.next.prev = null;
            return pseudoHead.next;
        }

        /* return the tail of the flatten list */
        public Node flattenDFS(Node prev, Node curr) {
            if (curr == null)
                return prev;
            curr.prev = prev;
            prev.next = curr;

            // the curr.next would be tempered in the recursive function
            Node tempNext = curr.next;

            Node tail = flattenDFS(curr, curr.child);
            curr.child = null;

            return flattenDFS(tail, tempNext);
        }

    }
}
