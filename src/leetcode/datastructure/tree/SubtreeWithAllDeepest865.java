package leetcode.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/
public class SubtreeWithAllDeepest865 {

    // time O(N)
    // espace O(N)
    private int maxDeep = Integer.MIN_VALUE;
    private TreeNode result;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        maxDeep(root, 0);
        return result;
    }

    private int maxDeep(TreeNode node, int deep) {
        if (node == null) {
            return deep;
        }
        int left = maxDeep(node.left, deep + 1);
        int right = maxDeep(node.right, deep + 1);
        int currentMax = Math.max(left, right);
        maxDeep = Math.max(maxDeep, currentMax);
        if (left == maxDeep && right == maxDeep) {
            result = node;
        }
        return currentMax;
    }

    


    //dfs 两次
    // 第一次 找出最深的depth
    // 第二次 判断当前node是不是最深的那个 
    //                      是 返回其parent
    //                      否 递归dfs
    // time O（N)
    // espace O(N)
    class DFS2Times {
        Map<TreeNode, Integer> depth;
        int max_depth;
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            depth = new HashMap<>();
            depth.put(null, -1);
            dfs(root, null);
            max_depth = -1;
            for (Integer d: depth.values())
                max_depth = Math.max(max_depth, d);
    
            return answer(root);
        }
    
        public void dfs(TreeNode node, TreeNode parent) {
            if (node != null) {
                depth.put(node, depth.get(parent) + 1);
                dfs(node.left, node);
                dfs(node.right, node);
            }
        }
    
        public TreeNode answer(TreeNode node) {
            if (node == null || depth.get(node) == max_depth)
                return node;
            TreeNode L = answer(node.left), R = answer(node.right);
            if (L != null && R != null) return node;
            if (L != null) return L;
            if (R != null) return R;
            return null;
        }
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
