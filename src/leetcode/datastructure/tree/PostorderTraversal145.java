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

    class Iterative {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root.left);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node == null)
                    continue;
                stack.push(node.right);
                stack.push(node.left);
                result.add(node.val);
            }
             Collections.reverse(result);
             return result;
        }
    }
}
