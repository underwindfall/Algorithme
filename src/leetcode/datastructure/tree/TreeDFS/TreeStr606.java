package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/construct-string-from-binary-tree/
public class TreeStr606 {
    // time O(n+m)
    // space O(n)
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        String res, left, right;
        left = tree2str(root.left);
        right = tree2str(root.right);
        if (left.equals("") && right.equals("")) {
            res = String.valueOf(root.val);
        } else if (!left.equals("") && right.equals("")) {
            res = String.valueOf(root.val) + "(" + left + ")";
        } else if (left.equals("") && !right.equals("")) {
            res = String.valueOf(root.val) + "()" + "(" + right + ")";
        } else {
            res = String.valueOf(root.val) + "(" + left + ")" + "(" + right + ")";
        }
        return res;
    }

    // time O(n+m)
    // space O(n)
    class Iterative {
        public String tree2str(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Set<TreeNode> vis = new HashSet<>();
            Deque<TreeNode> d = new ArrayDeque<>();
            d.addLast(root);
            while (!d.isEmpty()) {
                TreeNode t = d.pollLast();
                if (vis.contains(t)) {
                    sb.append(")");
                } else {
                    d.addLast(t);
                    sb.append("(");
                    sb.append(t.val);
                    if (t.right != null)
                        d.addLast(t.right);
                    if (t.left != null)
                        d.addLast(t.left);
                    else if (t.right != null)
                        sb.append("(");
                    vis.add(t);
                }
            }
            return sb.substring(1, sb.length() - 1);
        }

    }



    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

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
