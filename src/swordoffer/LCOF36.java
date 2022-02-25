package swordoffer;

// https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
public class LCOF36 {
    // time O(n)
    // space O(n)
    Node head = null, pre = null;
    
    public Node treeToDoublyList(Node root) {  
        if (root == null) return root;
        inOrder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }


    void inOrder(Node node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        if (pre == null) {
            head = node;
        } else {
            pre.right = node;
            node.left = pre;
        }
        pre = node;
        inOrder(node.right);
    }
    
    class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
