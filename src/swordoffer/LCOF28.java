package swordoffer;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
public class LCOF28 {
    // time O(n)
    // space O(n)
    class DFS {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return dfs(root, root);
        }

        boolean dfs(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if ((left != null & right == null) || (left == null && right != null)) {
                return false;
            }
            if (left.val == right.val) {
                return dfs(left.left, right.right) && dfs(left.right, right.left);
            }
            return false;
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        public boolean isSymmetric(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            if (root == null) {
                return true;
            }
            q.offer(root.left);
            q.offer(root.right);
            while (!q.isEmpty()) {
                TreeNode left = q.poll();
                TreeNode right = q.poll();
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                q.offer(left.left);
                q.offer(right.right);
                q.offer(left.right);
                q.offer(right.left);
            }
            return true;
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
