package leetcode.datastructure.tree;

// https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree-ii
public class LowestCommonAncestorII1644 {
    boolean pFound = false;
    boolean qFound = false;

    //time O(n)
    //space O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = LCA(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);
        if (root == p) {
            pFound = true;
            return root;
        }
        if (root == q) {
            qFound = true;
            return root;
        }
        return left == null ? right : right == null ? left : root;
    }

    class AnotherWay {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (!dfs(root, p) || !dfs(root, q)) return null;
    
            return dfs(root, p, q);        
        }
    
        TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = dfs(root.left, p, q);
            TreeNode right = dfs(root.right, p, q);
            
            if (left == null) {
                return right;   
            }
    
            if (right == null) {
                return left;
            } 
            
            return root;
        }
    
        boolean dfs(TreeNode root, TreeNode target) {
            
            if(root == null) return false;
            if(root.val == target.val) return true;
    
            return dfs(root.left, target) || dfs(root.right, target);
        }
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
