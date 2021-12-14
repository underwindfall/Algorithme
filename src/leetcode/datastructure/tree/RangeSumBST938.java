package leetcode.datastructure.tree;

import java.util.Stack;

// https://leetcode.com/problems/range-sum-of-bst/
public class RangeSumBST938 {
    // time O(n)
    // space O(h)
    class DFS {
        public int rangeSumBST(TreeNode root, int low, int high) {
            return dfs(root, low, high);
        }

        int dfs(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }
            if (root.val < low) {
                return dfs(root.right, low, high);
            } else if (root.val > high) {
                return dfs(root.left, low, high);
            } else {
                return dfs(root.left, low, high) + dfs(root.right, low, high) + root.val;
            }
        }
    }

    // time O(n)
    // space O(n)
    class Iterative {
        public int rangeSumBST(TreeNode root, int low, int high) {
            int ans = 0;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node != null) {
                    if (low <= node.val && node.val <= high) {
                        ans += node.val;
                    }
                    if (low < node.val) {
                        stack.push(node.left);
                    }
                    if (high > node.val) {
                        stack.push(node.right);
                    }
                }
            }
            return ans;
        }
    }

    @SuppressWarnings("unused")
    private class TreeNode {
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
