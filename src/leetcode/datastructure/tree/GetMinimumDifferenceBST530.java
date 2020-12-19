package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
public class GetMinimumDifferenceBST530 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int pre = -1;
    int ans = Integer.MAX_VALUE;

    // time O(N)
    // espace O(logN)
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        if (pre == -1) {
            pre = node.val;
        } else {
            ans = Math.min(ans, Math.abs(node.val - pre));
            pre = node.val;
        }
        dfs(node.right);
    }
}
