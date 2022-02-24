package swordoffer;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
public class LCOF27 {
    // time O(n)
    // space O(n)
    class DFS {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null)
                return root;
            if (root.left == null && root.right == null) {
                return root;
            }
            TreeNode newLeft = mirrorTree(root.left);
            TreeNode newRight = mirrorTree(root.right);
            root.left = newRight;
            root.right = newLeft;
            return root;
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        public TreeNode mirrorTree(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode tmp = q.poll();
                if (tmp == null)
                    continue;
                TreeNode left = tmp.left;
                tmp.left = tmp.right;
                tmp.right = left;
                if (tmp.left != null) {
                    q.offer(tmp.left);
                }
                if (tmp.right != null) {
                    q.offer(tmp.right);
                }
            }
            return root;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
