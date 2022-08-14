package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/binary-tree-paths/
public class BinaryTreePaths257 {
    // time O(N^2)
    // space O(N^2)
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        dfs(root, paths, new ArrayList<>());
        return paths;
    }

    void dfs(TreeNode root, List<String> path, List<String> curr) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            curr.add(root.val + "");
            path.add(String.join("->", curr));
            curr.remove(curr.size() - 1);
            return;
        }

        curr.add(root.val + "");
        dfs(root.left, path, curr);
        dfs(root.right, path, curr);
        curr.remove(curr.size() - 1);

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
