package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/maximum-binary-tree/
public class MaxiumBinaryTree654 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N*logN)
    // espace O(logN)
    TreeNode constructMaximumBinaryTree(int nums[]) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int maxVal = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        root.left = build(nums, lo, maxIndex - 1);
        root.right = build(nums, maxIndex + 1, hi);
        return root;
    }

}
