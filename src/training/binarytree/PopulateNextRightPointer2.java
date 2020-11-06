package training.binarytree;

import java.util.HashMap;
import java.util.Map;

public class PopulateNextRightPointer2 {

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

    // time:O(N)
    // espace: O(1)
    public Node connect(Node root) {
        if (root == null)
            return root;
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
                // 继续访问这一行的下一个节点
                cur = cur.next;
            }
            // 把下一层串联成一个链表之后，让他赋值给cur，
            // 后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }

    // time:O(N)
    // espace:O(height)
    Map<Integer, Node> map = new HashMap<>();

    public Node connectDfs(Node root) {
        dfs(root, 0);
        return root;
    }

    private void dfs(Node node, int depth) {
        if (node == null)
            return;
        if (map.containsKey(depth)) {
            map.get(depth).next = node;
        }
        map.put(depth, node);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

}
