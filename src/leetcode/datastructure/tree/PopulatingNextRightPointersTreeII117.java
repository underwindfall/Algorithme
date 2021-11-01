package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
public class PopulatingNextRightPointersTreeII117 {

    /**
     * 思路和116 一致 用一个pre节点控制
     * 
     * time O(n)
     * 
     * space O(n)
     */
    class LC116 {
        public Node connect(Node root) {
            if (root == null)
                return root;

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                Node pre = null;
                for (int i = 0; i < size; i++) {
                    Node node = q.poll();
                    if (pre != null) {
                        pre.next = node;
                    }
                    pre = node;
                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
            }
            return root;
        }
    }

    // time O(n)
    // space O(1)
    /**
     * 利用哨兵节点
     */
    class DummyNode {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }
            // cur我们可以把它看做是每一层的链表
            Node cur = root;
            while (cur != null) {
                // 遍历当前层的时候，为了方便操作在下一
                // 层前面添加一个哑结点（注意这里是访问
                // 当前层的节点，然后把下一层的节点串起来）
                Node dummy = new Node(0);
                // pre表示访下一层节点的前一个节点
                Node pre = dummy;
                // 然后开始遍历当前层的链表
                while (cur != null) {
                    if (cur.left != null) {
                        // 如果当前节点的左子节点不为空，就让pre节点
                        // 的next指向他，也就是把它串起来
                        pre.next = cur.left;
                        // 然后再更新pre
                        pre = pre.next;
                    }
                    // 同理参照左子树
                    if (cur.right != null) {
                        pre.next = cur.right;
                        pre = pre.next;
                    }
                    // 继续访问这样行的下一个节点
                    cur = cur.next;
                }
                // 把下一层串联成一个链表之后，让他赋值给cur，
                // 后续继续循环，直到cur为空为止
                cur = dummy.next;
            }
            return root;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

}
