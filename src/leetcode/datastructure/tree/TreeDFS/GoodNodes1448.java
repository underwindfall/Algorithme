package leetcode.datastructure.tree.TreeDFS;

// https://leetcode.cn/problems/count-good-nodes-in-binary-tree/
// time O(H)
// space O(N)
public class GoodNodes1448 {
    int count = 0;

    public int goodNodes(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root, Integer.MIN_VALUE);
        return count;
    }

    void dfs(TreeNode root, int max) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            count++;
            max = root.val;
        }
        dfs(root.left, max);
        dfs(root.right, max);
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
