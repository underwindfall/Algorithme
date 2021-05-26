package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

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
    // espace O(logN) H 树的高度
    class Dfs {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
            if (root.left == null && root.right == null) {
                return 1;
            }
            //如果左孩子和右孩子其中一个为空，那么需要返回比较大的那个孩子的深度
            int min_depth = Integer.MAX_VALUE;
            if (root.left != null) {
                min_depth = Math.min(minDepth(root.left), min_depth);
            }
            if (root.right != null) {
                min_depth = Math.min(minDepth(root.right), min_depth);
            }
            //3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
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
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left == null && node.right == null) {
                        return count;
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                count++;
            }
            return -1;
        }
    }
}
