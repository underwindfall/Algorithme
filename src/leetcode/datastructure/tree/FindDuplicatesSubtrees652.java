package leetcode.datastructure.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/find-duplicate-subtrees/
public class FindDuplicatesSubtrees652 {
    // 记录所有子树以及出现的次数
    Map<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    List<TreeNode> res = new LinkedList<>();

    // 可以序列化二叉树
    // time O(N^2) N 是二叉树上节点的数量
    // espace O(N^2)
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode node) {
        if (node == null) {
            return "#";
        }
        String left = traverse(node.left);
        String right = traverse(node.right);
        String subTree = left + "," + right + "," + node.val;

        memo.put(subTree, memo.getOrDefault(subTree, 0) + 1);
        if (memo.get(subTree) == 2) {
            res.add(node);
        }
        return subTree;
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
