package swordoffer;

// https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
public class LCOF26 {
    // time O(n)
    // space O(n)
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null && dfs(A, B)) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean dfs(TreeNode A, TreeNode B) {
        if (B == null)
            return true;
        if (A == null && A.val != B.val)
            return false;
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
