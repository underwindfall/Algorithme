package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
public class SecondMinimumValue671 {
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

    // time O(n)
    // espace O(logn)
    public int findSecondMinimumValue(TreeNode root) {
        // 每个节点的根结点一定是最小的
        if (root == null) {
            return -1;
        }
        if (root.left == null && root.right == null) {
            return -1;
        }
        int leftVal = root.left.val;
        int rightVal = root.right.val;
        if (leftVal == root.val) {
            leftVal = findSecondMinimumValue(root.left);
        }
        if (rightVal == root.val) {
            rightVal = findSecondMinimumValue(root.right);
        }
        if (leftVal != -1 && rightVal != -1) {
            return Math.min(leftVal, rightVal);
        }
        if (leftVal != -1) {
            return leftVal;
        }
        return rightVal;
    }
}
