package swordoffer;

//https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
public class LCOF55_II {
    // time O(n * logN)
    // space O(n)
    class DFS {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            int leftLevel = dfs(root.left);
            int rightLevel = dfs(root.right);
            return Math.abs(rightLevel - leftLevel) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        int dfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = dfs(root.left);
            int right = dfs(root.right);
            int level = Math.max(left, right);
            return level + 1;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
