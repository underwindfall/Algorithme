package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
public class PopulatingNextRightPointersTree116 {
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
    }

    /**
     * 思路 这里可以把子树部分当作一个localview去思考 通过前序遍历的方式来获取到当前tree的递归规律
     * 
     * // a ----> d
     * 
     * // b -> c ---> e f
     * 
     * cur = a; 递归规律是 1. a.left.next = a.right
     * 
     * 2. a.right.next = a.next.left
     * 
     * @param root
     * @return
     */
    // Pre-order遍历
    // time O(N) N是总过有多少节点
    // espace O(logN) n = 树的高度
    public Node connect(Node root) {
        if (root == null || root.right == null) {
            return root;
        }
        // 先处理root
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    class Recursive {
        // 前序遍历
        Node connect(Node root) {
            if (root == null)
                return null;
            connectTwoNode(root.left, root.right);
            return root;
        }

        // 辅助函数
        void connectTwoNode(Node node1, Node node2) {
            if (node1 == null || node2 == null) {
                return;
            }
            /**** 前序遍历位置 ****/
            // 将传入的两个节点连接
            node1.next = node2;

            // 连接相同父节点的两个子节点
            connectTwoNode(node1.left, node1.right);
            connectTwoNode(node2.left, node2.right);
            // 连接跨越父节点的两个子节点
            connectTwoNode(node1.right, node2.left);
        }
    }

    // 1.广度遍历
    // 2. 然后把所有的存储的点在next一下
    class BFS {

        Node connect(Node root) {
            if (root == null) {
                return root;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);// 相当于把数据加入到队列尾部
            while (!queue.isEmpty()) {
                // 每层的数量
                int levelSize = queue.size();
                // 前一个节点
                Node pre = null;
                for (int i = 0; i < levelSize; i++) {
                    // 出队
                    Node node = queue.poll();
                    // 记录前一个节点
                    if (pre != null) {
                        pre.next = node;
                    }
                    pre = node;
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return root;
        }

        @SuppressWarnings("unused")
        private void levelOrder(Node tree) {
            if (tree == null) {
                return;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(tree);// 相当于把数据加入到队列尾部
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.println("do something depends on question" + node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

}
