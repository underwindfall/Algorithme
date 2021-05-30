package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
public class PostorderTraversal145 {
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
    // espace O(N)
    class Recursive {
        List<Integer> result = new ArrayList<>();

        public List<Integer> postorderTraversal(TreeNode root) {
            dfs(root);
            return result;
        }

        void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            dfs(node.left);
            dfs(node.right);
            result.add(node.val);
        }
    }

    // time O(N)
    // espace O(N)
    // 后序遍历 left, right, root -> reverse -> root, right, left
    // 前序遍历 root, left , right -> 前序遍历 先进右节点 再进左节点
    class Iterative {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node == null)
                    continue;
                result.add(node.val);
                stack.push(node.left);
                stack.push(node.right);
            }
            Collections.reverse(result);
            return result;
        }
    }

    // time O(N)
    // espace O(N)
    class Another {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode prev = null;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == prev) {
                    result.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.add(root);
                    root = root.right;
                }
            }
            return result;
        }
    }
}
