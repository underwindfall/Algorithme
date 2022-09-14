package leetcode.datastructure.tree.TreeDFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree/
public class PseudoPalindromicPaths1457 {
    int count = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, new HashSet<>());
        return count;
    }

    void dfs(TreeNode node, Set<Integer> set) {
        if (set.contains(node.val)) {
            set.remove(node.val);
        } else {
            set.add(node.val);
        }

        if (node.left == null && node.right == null) {
            if (set.size() <= 1) {
                count++;
            }
        }

        if (node.left != null)
            dfs(node.left, set);
        if (node.right != null)
            dfs(node.right, set);

        if (set.contains(node.val)) {
            set.remove(node.val);
        } else {
            set.add(node.val);
        }

        // return;
    }

    class EspaceExplode {
        int count = 0;

        public int pseudoPalindromicPaths(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            dfs(root, new ArrayDeque<>(), ans);
            for (List<Integer> path : ans) {
                if (isPalindrom(path)) {
                    count++;
                }
            }
            return count;
        }

        void dfs(TreeNode node, Deque<Integer> path, List<List<Integer>> ans) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) {
                path.offerLast(node.val);
                ans.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }

            path.offerLast(node.val);
            dfs(node.left, path, ans);
            dfs(node.right, path, ans);
            path.removeLast();
        }

        boolean isPalindrom(List<Integer> list) {
            Set<Integer> set = new HashSet<>();
            for (int i : list) {
                if (set.contains(i)) {
                    set.remove(i);
                } else {
                    set.add(i);
                }
            }
            if (set.size() == 1 || set.size() == 0) {
                return true;
            } else {
                return false;
            }
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
