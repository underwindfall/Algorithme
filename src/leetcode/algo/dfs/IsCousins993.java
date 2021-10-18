package leetcode.algo.dfs;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/cousins-in-binary-tree/
public class IsCousins993 {
    // time O(h)
    // space O(h)
    class DFS {
        // 父亲节点
        TreeNode parentX;
        TreeNode parentY;
        // level tree
        int depthX;
        int depthY;

        public boolean isCousins(TreeNode root, int x, int y) {
            parentX = null;
            parentY = null;
            depthX = 0;
            depthY = 0;
            dfs(root, null, x, y, 0);
            return depthX == depthY && parentX != parentY;
        }

        void dfs(TreeNode root, TreeNode parent, int x, int y, int depth) {
            if (root == null)
                return;
            if (root.val == x) {// 找到X
                depthX = depth;
                parentX = parent;
            }
            if (root.val == y) {
                depthY = depth;
                parentY = parent;
            }
            dfs(root.left, root, x, y, depth + 1); // dfs左孩子 深度+1 然后left的父亲当然是当前root
            dfs(root.right, root, x, y, depth + 1);
        }
    }

    // time O(n)
    // space O(n)
    class DFSOptimazation {
        public boolean isCousins(TreeNode root, int x, int y) {
            int[] xi = dfs(root, null, 0, x);
            int[] yi = dfs(root, null, 0, y);
            return xi[1] == yi[1] && xi[0] != yi[0];
        }

        int[] dfs(TreeNode root, TreeNode fa, int depth, int t) {
            if (root == null) {
                return new int[] { -1, -1 }; // 使用 - 1 代表找不到parent
            }
            if (root.val == t) {
                return new int[] { fa != null ? fa.val : 1, depth };
            }
            int[] l = dfs(root.left, root, depth + 1, t);
            if (l[0] != -1) {
                return l;
            }
            return dfs(root.right, root, depth + 1, t);
        }
    }

    // time O(n)
    // space O(n)
    class BFS {
        public boolean isCousins(TreeNode root, int x, int y) {
            int[] xi = bfs(root, x);
            int[] yi = bfs(root, y);
            return xi[1] == yi[1] && xi[0] != yi[0];
        }

        int[] bfs(TreeNode root, int t) {
            Queue<Object[]> q = new LinkedList<>();// [cur, fa, depth]
            q.add(new Object[] { root, null, 0 });
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Object[] u = q.poll();
                    TreeNode cur = (TreeNode) u[0], fa = (TreeNode) u[1];
                    int depth = (int) u[2];
                    if (cur.val == t)
                        return new int[] { fa != null ? fa.val : 0, depth };
                    if (cur.left != null)
                        q.add(new Object[] { cur.left, cur, depth + 1 });
                    if (cur.right != null)
                        q.add(new Object[] { cur.right, cur, depth + 1 });
                }
            }
            return new int[] { -1, -1 };
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
