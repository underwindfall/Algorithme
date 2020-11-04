package training.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
/**
 * 给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，
**/


//     3
//    / \
//   9  20
//     /  \
//    15   7

   
public class MaxDepthsOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }
    // time:O(N) 其中 nn 为二叉树节点的个数。每个节点在递归中只被遍历一次
    // espace:O(Height) height -> tree 高度

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 广度优先算法
    // time : O(N)
    //espace : O(n)
    public int maxDepthBfs(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans++;
        }
        return ans;
    }

}
