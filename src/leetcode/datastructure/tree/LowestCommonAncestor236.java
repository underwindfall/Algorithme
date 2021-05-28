package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestor236 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // time O(logN) ~ O(N) worst case every node
    // espace (logN) ~ O(N) worst case every node 深度会达到N
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
