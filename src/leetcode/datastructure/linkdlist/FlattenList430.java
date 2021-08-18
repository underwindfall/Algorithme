package leetcode.datastructure.linkdlist;

//https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
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

    class SimpleWay {
        public Node flatten(Node head) {
            Node p = head;
            while (p != null) {
                if (p.child != null) {
                    Node next = p.next;
                    Node child = p.child;
                    p.next = child;
                    p.child = null;

                    child.prev = p;
                    while (child.next != null)
                        child = child.next;
                    child.next = next;

                    if (next != null)
                        next.prev = child;
                }
                p = p.next;
            }
            return head;
        }

    }

    /*
     * // Definition for a Node. class Node { public int val; public Node prev;
     * public Node next; public Node child; };
     */

    class Solution {
        private Node ans;
        private Node cur;

        public Node flatten(Node head) {
            if (head == null) {
                return null;
            }
            dfs(head);
            return ans;
        }

        // 看成二叉树的前序遍历
        private void dfs(Node node) {
            if (node == null) {
                return;
            }
            // 相当于前序遍历的主体，把遍历到的当前节点放入新的链表里
            if (cur == null) {
                ans = new Node();
                ans.val = node.val;
                cur = ans;
            } else {
                Node newNode = new Node();
                newNode.val = node.val;
                cur.next = newNode;
                newNode.prev = cur;
                cur = newNode;
            }
            // 优先迭代子节点，再迭代下一个节点
            dfs(node.child);
            dfs(node.next);
        }
    }

    class DFS {

        // 思路就是把这个链表的next当作右子树 child当作左子树 做前序遍历
        // time O(N)
        // espace O(N)
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
