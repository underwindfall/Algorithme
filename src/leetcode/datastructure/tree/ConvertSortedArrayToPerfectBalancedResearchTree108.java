package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToPerfectBalancedResearchTree108 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //题目思路 没啥说的很简单的将有序数组换成BST，能想到就是中序遍历 + balanced tree
    // 意为着root的需要取出后，左右的子数组长度不超过1 即可
    // time O(N)
    // espace O(logN)
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    // time O(N)
    // espace O(logN)
    TreeNode buildTree(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int index = (endIndex + startIndex) / 2;
        TreeNode root = new TreeNode(nums[index]);
        root.left = buildTree(nums, startIndex, index - 1);
        root.right = buildTree(nums, index + 1, endIndex);
        return root;
    }
}
