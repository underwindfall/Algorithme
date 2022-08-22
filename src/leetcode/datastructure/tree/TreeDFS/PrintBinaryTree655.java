package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.cn/problems/print-binary-tree/
public class PrintBinaryTree655 {
    // time O(H * 2 ^ H)
    // space O(H)
    class DFS {
        public List<List<String>> printTree(TreeNode root) {
            int height = getDepth(root);
            int m = height + 1;
            int n = (1 << (height + 1)) - 1;
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add("");
                }
                res.add(row);
            }
            dfs(res, root, 0, (n - 1) / 2, height);
            return res;
        }

        void dfs(List<List<String>> res, TreeNode root, int row, int col, int height) {
            res.get(row).set(col, Integer.toString(root.val));
            if (root.left != null) {
                dfs(res, root.left, row + 1, col - (1 << (height - row - 1)), height);
            }
            if (root.right != null) {
                dfs(res, root.right, row + 1, col + (1 << (height - row - 1)), height);
            }
        }

        int getDepth(TreeNode node) {
            int height = 0;
            if (node.left != null) {
                height = Math.max(height, getDepth(node.left) + 1);
            }
            if (node.right != null) {
                height = Math.max(height, getDepth(node.right) + 1);
            }
            return height;
        }
    }

    // time O(H * 2 ^ H)
    // space O(H)
    class BFS {
        public List<List<String>> printTree(TreeNode root) {
            int height = getDepth(root);
            int m = height + 1;
            int n = (1 << (height + 1)) - 1;
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add("");
                }
                res.add(row);
            }

            Queue<Tuple> q = new ArrayDeque<>();
            q.offer(new Tuple(root, 0, (n - 1) / 2));
            while (!q.isEmpty()) {
                Tuple tuple = q.poll();
                TreeNode node = tuple.node;
                int r = tuple.r, c = tuple.c;
                res.get(r).set(c, Integer.toString(node.val));
                if (node.left != null) {
                    q.offer(new Tuple(node.left, r + 1, c - (1 << (height - r - 1))));
                }
                if (node.right != null) {
                    q.offer(new Tuple(node.right, r + 1, c + (1 << (height - r - 1))));
                }
            }
            return res;
        }

        int getDepth(TreeNode node) {
            int res = -1;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(node);
            while (!q.isEmpty()) {
                int len = q.size();
                res++;
                while (len > 0) {
                    len--;
                    TreeNode t = q.poll();
                    if (t.left != null) {
                        q.offer(t.left);
                    }
                    if (t.right != null) {
                        q.offer(t.right);
                    }
                }
            }
            return res;
        }

        class Tuple {
            TreeNode node;
            int r;
            int c;

            Tuple(TreeNode node, int r, int c) {
                this.node = node;
            }
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
