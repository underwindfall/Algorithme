package training.binarytree;

import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

//https://leetcode-cn.com/problems/binary-tree-level-order-traversa
public class LevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }

    // 迭代
    // 层次遍历
    // time O(N)
    // espace O(N)
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }

        return res;

    }

    // 递归搜素
    // time : O(N)
    // espace: O(N)
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    private void recursive(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<Integer>());
        }

        levels.get(level).add(node.val);
        if (node.left != null) {
            recursive(node.left, level + 1);
        }
        if (node.right != null) {
            recursive(node.right, level + 1);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return levels;
        recursive(root, 0);
        return levels;
    }

}