package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
public class PreorderTraversal144 {
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

    // time O(N) 所有的节点
    // espace O(N) 树的高度最坏情况是高度和节点数相同
    class Recursive {
        List<Integer> result = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            dfs(root);
            return result;
        }

        void dfs(TreeNode node) {
            if (node == null) {
                return;
            }
            result.add(node.val);
            dfs(node.left);
            dfs(node.right);
        }
    }

    // time O(N)
    // espace O(N)
    class Iterative {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node == null)
                    continue;
                ret.add(node.val);
                stack.push(node.right); // 先右后左，保证左子树先遍历
                stack.push(node.left);
            }
            return ret;
        }
    }
}
