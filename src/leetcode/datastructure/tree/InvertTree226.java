package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class InvertTree226 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 思路 可以看到翻转二叉树就是把每个节点的左子树和右子树进行翻转
    // time O(N) N= num of tree node
    // espace O(logN) n=height of tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newLeftNode = invertTree(root.left);
        TreeNode newRightNode = invertTree(root.right);
        root.left = newRightNode;
        root.right = newLeftNode;
        return root;
    }
}
