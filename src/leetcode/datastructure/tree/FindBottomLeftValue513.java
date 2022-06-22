package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/find-bottom-left-tree-value/
public class FindBottomLeftValue513 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // time O(logN)
    // espace O(N)
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }

    // time O(logN)
    // espace O(N)
    class DFS {
        int maxDepth = 0, ans = 0;

        public int findBottomLeftValue(TreeNode root) {
            dfs(root, 1);
            return ans;
        }

        void dfs(TreeNode node, int depth) {
            if (node == null) {
                return;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
                ans = node.val;
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}
