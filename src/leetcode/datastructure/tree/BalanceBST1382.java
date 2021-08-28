package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/balance-a-binary-search-tree/
public class BalanceBST1382 {
    // 98 + 108
    // time O(n)
    // space O(n)
    public TreeNode balanceBST(TreeNode root) {
        // preorder to make sorted array then use this array to build perfect BST
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        return buildTree(nums, 0, nums.size() - 1);
    }

    void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }

    TreeNode buildTree(List<Integer> nums, int start, int end) {

        if (start > end) {
            return null;
        }
        int mid = (end - start) / 2 + start;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = buildTree(nums, start, mid - 1);
        root.right = buildTree(nums, mid + 1, end);
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
