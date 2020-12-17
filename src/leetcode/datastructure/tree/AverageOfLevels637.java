package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
public class AverageOfLevels637 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(logN)
    // espace O(N)
    class Dfs {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Integer> counts = new ArrayList<Integer>();
            List<Double> sums = new ArrayList<Double>();
            dfs(root, 0, counts, sums);
            List<Double> averages = new ArrayList<Double>();
            int size = sums.size();
            for (int i = 0; i < size; i++) {
                averages.add(sums.get(i) / counts.get(i));
            }
            return averages;
        }

        private void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
            if (root == null) {
                return;
            }
            if (level < sums.size()) {
                sums.set(level, sums.get(level) + root.val);
                counts.set(level, counts.get(level) + 1);
            } else {
                sums.add(1.0 * root.val);
                counts.add(1);
            }
            dfs(root.left, level + 1, counts, sums);
            dfs(root.right, level + 1, counts, sums);
        }

    }

    // time O(logN)
    // espace O(N)
    class Bfs {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> averages = new ArrayList<Double>();
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                double sum = 0;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                averages.add(sum / size);
            }
            return averages;
        }
    }
}
