package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class InvertTree226 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // dfs
    // 思路 可以看到翻转二叉树就是把每个节点的左子树和右子树进行翻转
    // time O(N) N= num of tree node
    // espace O(logN) n=height of tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newLeftNode = invertTree(root.left);
        TreeNode newRightNode = invertTree(root.right);
        root.left = newRightNode;
        root.right = newLeftNode;
        return root;
    }

    // bfs
    // time O(N)
    // espace O(N)
    class BFS {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            // 二叉树入队列，在迭代处理队列中的元素
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                // 每次都从队列中拿出一个节点 并交换左右子树
                TreeNode tmp = queue.poll();
                TreeNode left = tmp.left;
                tmp.left = tmp.right;
                tmp.right = left;

                // 如果当前左子树不为空入队列处理
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                // 同理
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            return root;
        }
    }
}
