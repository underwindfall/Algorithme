package swordoffer;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
public class LCOF34 {
    // time O(n)
    // space O(n)
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), res);
        return res;
    }

    void dfs(TreeNode node, int sum,List<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        if (sum == node.val && node.left == null && node.right == null) {
            path.add(node.val);
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        path.add(node.val);
        dfs(node.left, sum - node.val, path, res);
        dfs(node.right, sum - node.val, path, res);
        path.remove(path.size() - 1);
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
