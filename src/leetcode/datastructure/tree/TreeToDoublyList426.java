package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class TreeToDoublyList426 {

    //time O(n)
    //space O(n)
    Node head = null, pre = null;

    // left -> prev right -> succ
    public Node treeToDoublyList(Node root) {
        // left root right inOrder
        if (root == null)
            return root;
        inOrder(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre == null) {
            head = root;
        } else {
            root.left = pre;
            pre.right = root;
        }
        pre = root;
        inOrder(root.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
