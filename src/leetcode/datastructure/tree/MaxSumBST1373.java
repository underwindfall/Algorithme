package leetcode.datastructure.tree;

//bst
public class MaxSumBST1373 {
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


    // time O(n)
    // space O(h)
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        // left root right
        postOrderTraverse(root);
        return maxSum;
    }

    int[] postOrderTraverse(TreeNode root) {
        if (root == null) {
            // {min, max, sum}
            return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }
        int[] left = postOrderTraverse(root.left);
        int[] right = postOrderTraverse(root.right);
        // must be the BST tree
        if (!(left != null && right != null && root.val > left[1] && root.val < right[0])) {
            return null;
        }
        int sum = root.val + left[2] + right[2];
        maxSum = Math.max(sum, maxSum);
        int min = Math.min(root.val, left[0]);
        int max = Math.max(root.val, right[1]);
        return new int[] { min, max, sum };
    }
}
