package leetcode.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
public class GetAllElements1305 {
    // time O(n + m)
    // space O(n + m)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack1 = new Stack<>(), stack2 = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }
            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.left;
            }
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val <= stack2.peek().val)) {
                root1 = stack1.pop();
                res.add(root1.val);
                root1 = root1.right;
            } else {
                root2 = stack2.pop();
                res.add(root2.val);
                root2 = root2.right;
            }
        }
        return res;
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
