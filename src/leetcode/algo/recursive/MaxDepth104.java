package leetcode.algo.recursive;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxDepth104 {
    /* Definition for a binary tree node. */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Recursive {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int leftCount = maxDepth(root.left);
                int rightCount = maxDepth(root.right);
                return Math.max(leftCount, rightCount) + 1;
            }
        }
    }

    class Dfs {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Stack<TreeNode> nodeStack = new Stack<>();
            nodeStack.add(root);
            Stack<Integer> depth = new Stack<>();
            depth.add(1);
            int max = 0;
            while (!nodeStack.isEmpty()) {
                TreeNode node = nodeStack.pop();
                int level = depth.pop();
                max = Math.max(level, max);
                if (node.left != null) {
                    nodeStack.add(node.left);
                    depth.add(level + 1);
                }
                if (node.right != null) {
                    nodeStack.add(node.right);
                    depth.add(level + 1);
                }
            }
            return max;
        }
    }

    class Bfs {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 1;
            while (!queue.isEmpty()) {
                int sz = queue.size();
                for (int i = 0; i < sz; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                count++;
            }
            return count;
        }
    }
}
