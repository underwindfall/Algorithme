package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class LevelOrder102 {
    // time O(n)
    // space O(n)
    class DFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null)
                return res;
            dfs(root, res, 0);
            return res;
        }

        void dfs(TreeNode node, List<List<Integer>> res, int level) {
            if (node == null) {
                return;
            }
            if (res.size() == level) {
                res.add(new ArrayList<>());
            }

            res.get(level).add(node.val);
            dfs(node.left, res, level + 1);
            dfs(node.right, res, level + 1);
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null)
                return res;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> path = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    path.add(node.val);
                    if (node.left != null) {
                        q.offer(node.left);
                    }
                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
                res.add(path);
            }
            return res;
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
