package leetcode.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeaf129 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(H) == O(N)
    class DFS {
        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }

        int dfs(TreeNode node, int sum) {
            if (node == null) {
                return 0;
            } else if (node.left == null && node.right == null) {
                return sum * 10 + node.val;
            } else {
                sum = sum * 10 + node.val;
                return dfs(node.left, sum) + dfs(node.right, sum);
            }

        }
    }

    // time O(N)
    // espace O(H) == O(N)
    class BFS {
        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            Queue<Integer> nodeNum = new LinkedList<>();
            nodeQueue.offer(root);
            nodeNum.offer(root.val);
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.poll();
                int num = nodeNum.poll();
                if (node.left == null && node.right == null) {
                    sum += num;
                } else {
                    if (node.left != null) {
                        nodeQueue.offer(node.left);
                        nodeNum.offer(num * 10 + node.left.val);
                    }
                    if (node.right != null) {
                        nodeQueue.offer(node.right);
                        nodeNum.offer(num * 10 + node.right.val);
                    }
                }
            }
            return sum;
        }
    }
}
