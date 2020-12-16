package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/longest-univalue-path/
public class LongestUnivaluePath687 {
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
    // time O(N)
    // espace O(logN)
    class Dfs {
        int ans = 0; // 结果

        public int longestUnivaluePath(TreeNode root) {
            helper(root);
            return ans;
        }

        // 搜索以root为起点的最长同值路径
        // 要么经过左子树，要么经过右子树
        public int helper(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int maxLength = 0; // 以root为起点的最长同值路径
            int leftLength = helper(root.left); // 以root.left为起点的最长同值路径
            int rightLength = helper(root.right); // 以root.right为起点的最长同值路径
            // 情况2，不需要更新maxLength，但要更新结果
            if (root.left != null && root.right != null && root.val == root.left.val && root.val == root.right.val) {
                ans = Math.max(ans, leftLength + rightLength + 2);
            }
            // 从左右子树中选取最长同值路径
            if (root.left != null && root.val == root.left.val) {
                maxLength = leftLength + 1;
            }
            if (root.right != null && root.val == root.right.val) {
                maxLength = Math.max(maxLength, rightLength + 1);
            }
            // 更新结果
            ans = Math.max(ans, maxLength);
            return maxLength;
        }
    }
}
