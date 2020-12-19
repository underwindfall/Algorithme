package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
public class InorderTraversal94 {
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

    class Recursive {
        List<Integer> result = new ArrayList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            dfs(root);
            return result;
        }

        void dfs(TreeNode node) {
            if (node == null)
                return;
            dfs(node.left);
            result.add(node.val);
            dfs(node.right);
        }
    }

    class Iterative {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (!stack.isEmpty() || cur != null) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                //回退这个有点精髓
                TreeNode node = stack.pop();
                result.add(node.val);
                cur = node.right;
            }
            return result;
        }
    }
}
