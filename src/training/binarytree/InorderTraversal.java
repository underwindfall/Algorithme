package training.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
/**
 * 给定一个二叉树，返回它的中序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3] 1 \ 2 / 3
 * 
 * 输出: [1,3,2]
 * 
 * 
 */
public class InorderTraversal {
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

    // morris
    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
    // time : O(N)
    // espace O(1)
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root, leftTree = null;
        while (curr != null) {
            leftTree = curr.left;
            if (leftTree != null) {
                while (leftTree.right != null && leftTree.right != curr) {
                    leftTree = leftTree.right;
                }
                if (leftTree.right == null) {
                    leftTree.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    leftTree.right = null;
                }
            }
            result.add(curr.val);
            curr = curr.right;
        }

        return result;
    }

    // 时间复杂度：O(n)O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
    // 空间复杂度：O(n)O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)O(n) 的级别。
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            // right - root - left
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    // recurisve
    // time : O(N)
    // espace: O(N)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(result, root);
        return result;
    }

    private void inOrder(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(result, node.left);
        result.add(node.val);
        inOrder(result, node.right);
    }
}
