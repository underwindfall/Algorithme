package leetcode.datastructure.tree;

//https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestorBST235 {
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
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
