package leetcode.datastructure.tree;

//https://leetcode-cn.com/problems/binary-tree-longest-consecutive-sequence-ii/
public class LongestConsecutiveII549 {
    // time O(N)
    // espace O(N)
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        int parent = dfs(root, 1) + dfs(root, -1) - 1;
        return Math.max(parent, Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));
    }

    int dfs(TreeNode node, int diff) {
        if (node == null) {
            return 0;
        }

        int left = 0;
        int right = 0;

        if (node.left != null && node.left.val == node.val + diff) {
            left = dfs(node.left, diff);
        }
        if (node.right != null && node.right.val == node.val + diff) {
            right = dfs(node.right, diff);
        }

        int length = Math.max(left, right) + 1;
        return length;
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
