package leetcode.algo.recursive;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
public class GenerateTrees95 {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<TreeNode> generateTrees(int n) {
            if (n < 1)
                return new ArrayList<>();
            return helper(1, n);
        }

        public List<TreeNode> helper(int start, int end) {
            List<TreeNode> list = new ArrayList<>();

            if (start > end) {
                // 如果当前子树为空，不加null行吗？
                list.add(null);
                return list;
            }

            for (int i = start; i <= end; i++) {
                // 想想为什么这行不能放在这里，而放在下面？
                // TreeNode root = new TreeNode(i);
                List<TreeNode> left = helper(start, i - 1);
                List<TreeNode> right = helper(i + 1, end);

                // 固定左孩子，遍历右孩子
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        list.add(root);
                    }
                }
            }
            return list;
        }
    }

    class Sample {
        public TreeNode createBinaryTree(int n) {
            return helper(1, n);
        }

        public TreeNode helper(int start, int end) {
            if (start > end)
                return null;

            // 这里可以选择从start到end的任何一个值做为根结点，
            // 这里选择它们的中点，实际上，这样构建出来的是一颗平衡二叉搜索树
            int val = (start + end) / 2;
            TreeNode root = new TreeNode(val);

            root.left = helper(start, val - 1);
            root.right = helper(val + 1, end);

            return root;
        }
    }

}
