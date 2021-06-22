package leetcode.algo.backtrack;

//https://leetcode-cn.com/problems/longest-univalue-path/
public class LongestUnivaluePath687 {
    // time O(logN)
    // espace O(N)
    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return ans;
        dfs(root);
        return ans;
    }

    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        int pl = 0;
        int pr = 0;
        if (root.left != null && root.val == root.left.val) {
            pl = left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            pr = right + 1;
        }
        ans = Math.max(ans, pr + pl);
        return Math.max(pl, pr);
    }

    class TreeNode {
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
}
