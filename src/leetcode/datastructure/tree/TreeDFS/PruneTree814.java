package leetcode.datastructure.tree.TreeDFS;

// https://leetcode.cn/problems/binary-tree-pruning/
public class PruneTree814 {
    // time O(N)
    // space O(N)
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode lTree = pruneTree(root.left);
        TreeNode rTree = pruneTree(root.right);
        root.left = lTree;
        root.right = rTree;
        if (root.left == null && root.right == null) {
            if (root.val == 0) {
                return null;
            }
            return root;
        }
        return root;
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
