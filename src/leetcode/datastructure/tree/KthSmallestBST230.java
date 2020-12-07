package leetcode.datastructure.tree;

import java.util.LinkedList;
// https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestBST230 {
    public class TreeNode {
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

    // time O(N)
    // espace O(N)
    class InOrderTraverse {
        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return res;
        }

        int res = 0;
        int rank = 0;

        private void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            traverse(root.left, k);
            rank++;
            if (k == rank) {
                res = root.val;
                return;
            }
            traverse(root.right, k);
        }

    }

    // time :O(H+K) h = tree height k = kth number
    // espace :O(H+K)
    class Recursive {
        public int kthSmallest(TreeNode root, int k) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            while (true) {
                while (root != null) {
                    stack.add(root);
                    root = root.left;
                }
                root = stack.removeLast();
                if (--k == 0) {
                    return root.val;
                }
                root = root.right;
            }
        }

    }

}
