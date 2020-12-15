package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/sum-of-left-leaves/description/
public class SumOfLeftLeaves404 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(N)
    class Dfs {
        public int sumOfLeftLeaves(TreeNode root) {
            return root == null ? 0 : dfs(root);
        }

        int dfs(TreeNode node) {
            int ans = 0;
            if (node.left != null) {
                ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
            }
            if (node.right != null && !isLeafNode(node.right)) {
                ans += dfs(node.right);
            }
            return ans;
        }

        boolean isLeafNode(TreeNode node) {
            return node.left == null && node.right == null;
        }
    }

    // time O(N)
    // espace O(N)
    class Bfs {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int ans = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (isLeafNode(node.left)) {
                        ans += node.left.val;
                    } else {
                        queue.offer(node.left);
                    }
                }
                if (node.right != null) {
                    if (!isLeafNode(node.right)) {
                        queue.offer(node.right);
                    }
                }
            }
            return ans;
        }

        boolean isLeafNode(TreeNode node) {
            return node.left == null && node.right == null;
        }

    }
}
