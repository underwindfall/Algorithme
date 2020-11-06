package training.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

// 初始状态下，所有 next 指针都被设置为 NULL。

// https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/
public class PopulateNextRightPointer {
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

    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    void dfs(Node root) {
        if (root == null) {
            return;
        }
        Node left = root.left;
        Node right = root.right;
        // 配合动画演示理解这段，以root为起点，将整个纵深这段串联起来
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        // 递归的调用左右节点，完成同样的纵深串联
        dfs(root.left);
        dfs(root.right);
    }

    public Node connectbfs(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Node> listNodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                listNodes.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            for (int j = 0; j < listNodes.size(); j++) {
                if (j < listNodes.size() - 1) {
                    listNodes.get(j).next = listNodes.get(j + 1);
                } else {
                    listNodes.get(j).next = null;
                }

            }

        }
        return root;
    }

    public Node connectbfs2(Node root) {
        if (root == null) {
            return root;
        }

        // 初始化队列同时将第一层节点加入队列中，即根节点
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(root);

        // 外层的 while 循环迭代的是层数
        while (!queue.isEmpty()) {

            // 记录当前队列大小
            int size = queue.size();

            // 遍历这一层的所有节点
            for (int i = 0; i < size; i++) {

                // 从队首取出元素
                Node node = queue.poll();

                // 连接
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                // 拓展下一层节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        // 返回根节点
        return root;

    }

    // 思想
    // 如果处于同一层当中， 共享同一个父亲节点 node.left.next = node.right
    // 如果处于同一层当中， 不共享同一个父亲节点 最左边的节点可以可以类似 node.left.next = node.right
    // 中间部分的右节点 就是跨父亲的节点的左节点 node.right.next = node.next.left
    // 前提是 node.next !=null

    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }

        Node mostLeft = root;

        while (mostLeft.left != null) {
            Node head = mostLeft;
            while (head != null) {
                head.left.next = head.right;
                // 开始连接中间部分
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            mostLeft = mostLeft.left;
        }
        return root;
    }

}
