package leetcode.datastructure.tree;

import java.util.LinkedList;

// https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
public class MinDepth111 {
    public class TreeNode {
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

    // time O(N)
    // espace O(logN)
    class Dfs {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (root.left == null && root.right == null) {
                return 1;
            }

            int min_depth = Integer.MAX_VALUE;
            if (root.left != null) {
                min_depth = Math.min(minDepth(root.left), min_depth);
            }
            if (root.right != null) {
                min_depth = Math.min(minDepth(root.right), min_depth);
            }

            return min_depth + 1;
        }
    }

    // 同样，我们可以想到使用广度优先搜索的方法，遍历整棵树。
    // 当我们找到一个叶子节点时，直接返回这个叶子节点的深度。广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小。
    // time O(N)
    // espace O(N)
    class Bfs {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.removeFirst();
                    if (node.left == null && node.right == null) {
                        return count;
                    }
                    if (node.left != null) {
                        queue.addLast(node.left);
                    }
                    if (node.right != null) {
                        queue.addLast(node.right);
                    }
                }
                count++;
            }
            return -1;
        }
    }
}