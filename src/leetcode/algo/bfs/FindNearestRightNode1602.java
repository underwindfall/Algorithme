package leetcode.algo.bfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/find-nearest-right-node-in-binary-tree/
public class FindNearestRightNode1602 {

    // time O(n)
    // space O(n)
    class BFS {
        public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            TreeNode curr = root;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    curr = q.poll();
                    if (curr == u) {
                        if (i == size - 1) {
                            return null; // if it is last node in the level
                        } else {
                            return q.poll(); // otherwise, there exists a right node on same level
                        }
                    }
                    if (curr.left != null) {
                        q.offer(curr.left);
                    }
                    if (curr.right != null) {
                        q.offer(curr.right);
                    }
                }
            }
            return null;
        }
    }

    // time O(n)
    // space O(n)
    class DFS {
        int foundLevel = -1;
        public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
            return dfs(root, u, 0);
        }

        TreeNode dfs(TreeNode root, TreeNode u, int level) {
            if (root == null) {
                return null;
            }
            if (level == foundLevel) {
                return root;
            }
            if (root.val == u.val) {
                foundLevel = level;
                return null;
            }
            TreeNode left = dfs(root.left, u, level + 1);
            return left == null ? dfs(root.right, u, level + 1) : left;
        }
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
