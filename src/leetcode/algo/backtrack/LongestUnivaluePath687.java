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

    // time O(n^2)
    // space O(n^2)
    class DoubleDfs {
        public int longestUnivaluePath(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int longestWithRoot = dfs(root.left, root.val) + dfs(root.right, root.val);
            int longestWithoutRoot = Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right));
            return Math.max(longestWithoutRoot, longestWithRoot);
        }

        int dfs(TreeNode root, int val) {
            if (root == null) {
                return 0;
            }

            if (root.val != val) {
                return 0;
            }
            return Math.max(dfs(root.left, val), dfs(root.right, val)) + 1;
        }
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
