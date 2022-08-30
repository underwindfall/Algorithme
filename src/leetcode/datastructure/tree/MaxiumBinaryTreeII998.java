package leetcode.datastructure.tree;

// https://leetcode.cn/problems/maximum-binary-tree-ii/
public class MaxiumBinaryTreeII998 {

    // time O(N)
    // espace O(1)
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // 找到底部
        if (root == null) {
            return new TreeNode(val);
        }
        // 找到大于的节点
        if (root.val < val) {
            TreeNode treeNode = new TreeNode(val);
            treeNode.left = root;
            return treeNode;
        }
        // 遍历右节点
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }

    // time O(n)
    // space O(1)
    class Iterative {
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            TreeNode prev = null, curr = root;
            TreeNode node = new TreeNode(val);
            while (curr != null && curr.val > val) {
                prev = curr;
                curr = curr.right;
            }
            if (prev == null) {
                node.left = root;
                return node;
            } else {
                prev.right = node;
                node.left = curr;
                return root;
            }
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
