package interview.datadog;

import java.util.Stack;

//https://leetcode-cn.com/problems/path-sum/
public class PathSum112 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(N)
    // espace O(logN)
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // time O(N)
    // space O(logN)
    class DFSInterative {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null)
                return false;
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<>(root, targetSum));
            while (!stack.isEmpty()) {
                Pair<TreeNode, Integer> p = stack.pop();
                TreeNode node = p.getKey();
                int sum = p.getValue();
                if (node.left == null && node.right == null) {
                    if (node.val == sum)
                        return true;
                }
                if (node.left != null) {
                    stack.push(new Pair<>(node.left, sum - node.val));
                }
                if (node.right != null) {
                    stack.push(new Pair<>(node.right, sum - node.val));
                }
            }
            return false;
        }

        class Pair<A,B> {
            TreeNode key;
            int value;

            Pair(TreeNode key, int value) {
                this.key = key;
                this.value = value;
            }

            public TreeNode getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }
        }
    }
}
