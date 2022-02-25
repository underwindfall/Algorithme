package swordoffer;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
public class LCOF54 {
    // time O(n)
    // space O(n)
    class DFS {
        int k = -1;
        int ans = -1;

        public int kthLargest(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            this.k = k;
            dfs(root);
            return ans;
        }

        void dfs(TreeNode root) {
            if (root == null)
                return;
            dfs(root.right);
            k--;
            if (k == 0)
                ans = root.val;
            dfs(root.left);
        }
    }

    // time O(n)
    // space O(n)
    class PostOrder {

        public int kthLargest(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            List<Integer> ans = new ArrayList<>();
            dfs(root, ans);
            return ans.get(k - 1);
        }

        void dfs(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }
            dfs(node.right, res);
            res.add(node.val);
            dfs(node.left, res);
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
