package leetcode.datastructure.tree.TreeDFS;

import java.util.Stack;

// https://leetcode.com/problems/increasing-order-search-tree/
public class IncreaseOrderTree897 {

    // time O(N)
    // space O(N)
    TreeNode curr;
    public TreeNode increasingBST(TreeNode root) {
       TreeNode ans = new TreeNode(0);
       curr = ans;
       inOrder(root);
       return ans.right;
    }
    
    
    
    void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        node.left = null;
        curr.right = node;
        curr = node;
        inOrder(node.right);
    }

    // time O(N)
    // space O(N)
    public TreeNode increasingBSTStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return root;
        }
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode newRoot = null;
        while (!stack.isEmpty() || curr != null) {
            // keep going left, util you hit a null
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // pop from the stack and create the new root if not already created
            curr = stack.pop();
            if (newRoot == null) {
                newRoot = curr;
            }

            // assign the current element to previous element's right
            if (prev != null) {
                prev.right = curr;
                curr.left = null;
            }
            // point prev to cur and move curr to right
            prev = curr;
            curr = curr.right;
        }
        return newRoot;
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
