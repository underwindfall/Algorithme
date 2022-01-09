package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/sum-of-left-leaves/description/
public class SumOfLeftLeaves404 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(N)
    class Dfs {
        int count = 0;

        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return count;
            }
            dfs(root.left, true);
            dfs(root.right, false);
            return count;
        }

        void dfs(TreeNode node, boolean isFromLeft) {
            if (node == null) {
                return;
            }
            if (isFromLeft) {
                if (node.left == null && node.right == null) {
                    count += node.val;
                } else {
                    dfs(node.left, true);
                    dfs(node.right, false);
                }
            } else {
                dfs(node.left, true);
                dfs(node.right, false);
            }
        }
    }

    // time O(N)
    // espace O(N)
    class Bfs {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int ans = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (isLeafNode(node.left)) {
                        ans += node.left.val;
                    } else {
                        queue.offer(node.left);
                    }
                }
                if (node.right != null) {
                    if (!isLeafNode(node.right)) {
                        queue.offer(node.right);
                    }
                }
            }
            return ans;
        }

        boolean isLeafNode(TreeNode node) {
            return node.left == null && node.right == null;
        }

    }
}
